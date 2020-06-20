package utils;

public interface ImageViewAble {

	public default ImageView getImageView() {
		return MapImageViews.INSTANCE.getImageViewsMap().getValue(this);
	}

	public default void reverseSelectImageView() {
		SelectImageViewManager.INSTANCE.reverseSelectImageViewAble(this);
	}

}
