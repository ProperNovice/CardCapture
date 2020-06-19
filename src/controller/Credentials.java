package controller;

import utils.ArrayList;
import utils.NumbersPair;
import utils.SelectImageViewPool;

public enum Credentials {

	INSTANCE;

	public final String primaryStageTitle = "Card Capture";
	public final double gapBetweenBorders = 25, textHeight = 50, selectImageViewAbleRatio = 1.0 / 2,
			selectEventHandlerAbleWidth = 100;
	public ArrayList<Class<?>> lineCastExcludeList = new ArrayList<Class<?>>();
	public NumbersPair dFrame, dGapBetweenComponents, dGapBetweenComponentsLineCast, dCard;
	public NumbersPair cTextPanel;

	private Credentials() {

		this.lineCastExcludeList.addLast(SelectImageViewPool.class);

		double x = 0, y = 0;

		this.dFrame = new NumbersPair(1920, 1080);
		this.dGapBetweenComponents = new NumbersPair(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		this.cTextPanel = new NumbersPair(x, y);

		x = 200;
		y = 290;
		this.dCard = new NumbersPair(x, y);

	}

}
