package card;

import enums.ELayerZ;
import enums.ESuit;
import enums.EValue;
import utils.EventHandler.EventHandlerAble;
import utils.Flow;
import utils.ImageView;
import utils.ImageViewAble;

public class Card implements ImageViewAble, EventHandlerAble {

	private EValue eValue = null;
	private ESuit eSuit = null;
	private Card cardClone = null;

	public Card(EValue eValue, ESuit eSuit) {

		this(eValue, eSuit, ELayerZ.DEFAULT);

		this.cardClone = new Card(eValue, eSuit, ELayerZ.PANEL_CARD);
		this.cardClone.getImageView().setVisible(false);

	}

	private Card(EValue eValue, ESuit eSuit, ELayerZ eLayerZ) {

		this.eValue = eValue;
		this.eSuit = eSuit;

		String path = "cards/";
		path += eValue.getFileName();
		path += "_";
		path += this.eSuit;
		path += ".png";

		new ImageView(path, this, eLayerZ);
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

	public Card getCardClone() {
		return this.cardClone;
	}

}
