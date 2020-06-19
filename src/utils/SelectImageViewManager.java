package utils;

import controller.Credentials;
import utils.EventHandler.EventHandlerAble;

public enum SelectImageViewManager {

	INSTANCE;

	private HashMap<ImageViewAble, SelectImageViewPool> hashMapImageViewAble = new HashMap<ImageViewAble, SelectImageViewPool>();
	private HashMap<EventHandlerAble, SelectImageViewPool> hashMapEventHandlerAble = new HashMap<EventHandlerAble, SelectImageViewPool>();

	private SelectImageViewManager() {

	}

	public void reverseSelectImageViewAble(Object object) {

		if (!(object instanceof ImageViewAble))
			ShutDown.INSTANCE.execute();

		ImageViewAble imageViewAble = (ImageViewAble) object;

		if (!this.hashMapImageViewAble.containsKey(imageViewAble))
			addSelectImageViewAble(imageViewAble);
		else
			releaseSelectImageViewAble(imageViewAble);

	}

	public void addSelectImageViewAble(ImageViewAble imageViewAble) {

		if (this.hashMapImageViewAble.containsKey(imageViewAble))
			ShutDown.INSTANCE.execute();

		SelectImageViewPool selectImageView = ObjectPool.INSTANCE.acquire(SelectImageViewPool.class);
		this.hashMapImageViewAble.put(imageViewAble, selectImageView);

		selectImageView.getImageView().setVisible(true);

		ImageView imageView = imageViewAble.getImageView();

		double dimension = Math.min(imageView.getWidth(), imageView.getHeight());
		dimension *= Credentials.INSTANCE.selectImageViewAbleRatio;

		selectImageView.getImageView().setWidth(dimension);
		selectImageView.getImageView().relocateCenter(imageView.getCenter());

	}

	public void addSelectCoordinates(double x, double y, EventHandlerAble eventHandlerAble) {

		if (this.hashMapEventHandlerAble.containsKey(eventHandlerAble))
			ShutDown.INSTANCE.execute();

		SelectImageViewPool selectImageView = ObjectPool.INSTANCE.acquire(SelectImageViewPool.class);
		this.hashMapEventHandlerAble.put(eventHandlerAble, selectImageView);

		selectImageView.getImageView().setVisible(true);

		double width = Credentials.INSTANCE.selectEventHandlerAbleWidth;
		selectImageView.getImageView().setWidth(width);
		selectImageView.getImageView().relocateCenter(x, y);

	}

	public void addSelectCoordinates(NumbersPair numbersPair, EventHandlerAble eventHandlerAble) {
		addSelectCoordinates(numbersPair.x, numbersPair.y, eventHandlerAble);
	}

	public void releaseSelectImageViewAble(ImageViewAble imageViewAble) {

		if (!this.hashMapImageViewAble.containsKey(imageViewAble))
			ShutDown.INSTANCE.execute();

		SelectImageViewPool selectImageView = this.hashMapImageViewAble.getValue(imageViewAble);
		selectImageView.getImageView().setVisible(false);
		ObjectPool.INSTANCE.release(selectImageView);

		this.hashMapImageViewAble.remove(imageViewAble);

	}

	public void releaseSelectCoordinates(EventHandlerAble eventHandlerAble) {

		if (!this.hashMapEventHandlerAble.containsKey(eventHandlerAble))
			ShutDown.INSTANCE.execute();

		SelectImageViewPool selectImageView = this.hashMapEventHandlerAble.getValue(eventHandlerAble);
		selectImageView.getImageView().setVisible(false);
		ObjectPool.INSTANCE.release(selectImageView);

		this.hashMapEventHandlerAble.remove(eventHandlerAble);

	}

	public void releaseSelectImageViews() {

		for (ImageViewAble imageViewAble : this.hashMapImageViewAble.clone())
			releaseSelectImageViewAble(imageViewAble);

		for (EventHandlerAble eventHandlerAble : this.hashMapEventHandlerAble.clone())
			releaseSelectCoordinates(eventHandlerAble);

	}

	public boolean isSelectedImageViewAble(ImageViewAble imageViewAble) {
		return this.hashMapImageViewAble.containsKey(imageViewAble);
	}

	public boolean isSelectedCoordinates(EventHandlerAble eventHandlerAble) {
		return this.hashMapEventHandlerAble.containsKey(eventHandlerAble);
	}

	public void handleMouseButtonPressedPrimary(SelectImageViewPool selectImageView) {
		getEventHandlerAble(selectImageView).handleMouseButtonPressedPrimary();
	}

	public void handleMouseButtonPressedSecondary(SelectImageViewPool selectImageView) {
		getEventHandlerAble(selectImageView).handleMouseButtonPressedSecondary();
	}

	public void handleMouseEntered(SelectImageViewPool selectImageView) {
		getEventHandlerAble(selectImageView).handleMouseEntered();
	}

	public void handleMouseExited(SelectImageViewPool selectImageView) {

		EventHandlerAble eventHandlerAble = getEventHandlerAble(selectImageView);

		if (eventHandlerAble != null)
			eventHandlerAble.handleMouseExited();

	}

	private EventHandlerAble getEventHandlerAble(SelectImageViewPool selectImageView) {

		EventHandlerAble eventHandlerAble = null;

		if (this.hashMapImageViewAble.containsValue(selectImageView)) {

			ImageViewAble imageViewAble = this.hashMapImageViewAble.getKey(selectImageView);
			ImageView imageView = imageViewAble.getImageView();
			eventHandlerAble = imageView.getEventHandlerAble();

		} else if (this.hashMapEventHandlerAble.containsValue(selectImageView))
			eventHandlerAble = this.hashMapEventHandlerAble.getKey(selectImageView);

		return eventHandlerAble;

	}

	public int sizeComplete() {
		return this.hashMapImageViewAble.size() + this.hashMapEventHandlerAble.size();
	}

}
