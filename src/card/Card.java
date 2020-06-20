package card;

import enums.ESuit;
import enums.EValue;
import utils.EventHandler.EventHandlerAble;
import utils.Flow;
import utils.ImageView;
import utils.ImageViewAble;

public class Card implements ImageViewAble, EventHandlerAble {

	private EValue eValue = null;
	private ESuit eSuit = null;

	public Card(EValue eValue, ESuit eSuit) {

		this.eValue = eValue;
		this.eSuit = eSuit;
		createImageView();

	}

	private void createImageView() {

		String path = "cards/";
		path += eValue.getFileName();
		path += "_";
		path += this.eSuit;
		path += ".png";

		new ImageView(path, this);
		getImageView().setBack("cards/back.png");
		getImageView().flipBack();

	}

	@Override
	public void handleMouseButtonPressedPrimary() {

		Flow.INSTANCE.getCurrentGameState().handleCardPressed(this);

		if (getImageView().isFlippedBack())
			return;

		print();

	}

	public void print() {

		System.out.println("value -> " + this.eValue);
		System.out.println("suit -> " + this.eSuit);

		if (this.eValue.getPointValue() != -1)
			System.out.println("point value -> " + this.eValue.getPointValue());

		System.out.println();

	}

	public EValue getEValue() {
		return this.eValue;
	}

	public ESuit getESuit() {
		return this.eSuit;
	}

}
