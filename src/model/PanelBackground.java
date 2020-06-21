package model;

import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;
import utils.Flow;
import utils.ImageView;
import utils.ImageViewAble;

public class PanelBackground implements ImageViewAble, EventHandlerAble {

	public PanelBackground() {

		new ImageView("misc/backgroundDark.png", this, ELayerZ.PANEL_BACKGROUND);
		getImageView().setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getCurrentGameState().handlePanelPressed();
	}

}
