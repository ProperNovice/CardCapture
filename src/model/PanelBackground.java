package model;

import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public class PanelBackground implements ImageViewAble, EventHandlerAble {

	public PanelBackground() {

		new ImageView("misc/backgroundLight.png", this, ELayerZ.PANEL_BACKGROUND);
		getImageView().setVisible(false);

	}

}
