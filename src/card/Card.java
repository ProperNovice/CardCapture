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
	}

	public EValue getEValue() {
		return this.eValue;
	}

	public ESuit getESuit() {
		return this.eSuit;
	}

}
