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
	public NumbersPair cTextPanel, cDeckPlayer, cBoardPlayer, cDiscardPilePlayer, cDeckEnemy, cBoardEnemy,
			cDiscardPileEnemy;

	private Credentials() {

		int optionLines = 4;

		this.lineCastExcludeList.addLast(SelectImageViewPool.class);

		double x = 0, y = 0;

		x = 200;
		y = 290;
		this.dCard = new NumbersPair(x, y);

		this.dGapBetweenComponents = new NumbersPair(4, 4);
		this.dGapBetweenComponentsLineCast = this.dGapBetweenComponents;

		x = 2 * this.gapBetweenBorders + 6 * this.dCard.x + 5 * this.dGapBetweenComponents.x;
		y = 2 * this.gapBetweenBorders + 2 * this.dCard.y + 2 * this.dGapBetweenComponents.x
				+ optionLines * this.textHeight;
		this.dFrame = new NumbersPair(x, y);

		x = this.dFrame.x / 2 - 3 * this.dCard.x - 2.5 * this.dGapBetweenComponents.x;
		y = this.gapBetweenBorders;
		this.cDiscardPileEnemy = new NumbersPair(x, y);

		x = this.cDiscardPileEnemy.x + this.dCard.x + this.dGapBetweenComponents.x;
		y = this.cDiscardPileEnemy.y;
		this.cDeckEnemy = new NumbersPair(x, y);

		x = this.cDeckEnemy.x + 4 * (this.dCard.x + this.dGapBetweenComponents.x);
		y = this.cDeckEnemy.y;
		this.cBoardEnemy = new NumbersPair(x, y);

		x = this.cDiscardPileEnemy.x;
		y = this.cDiscardPileEnemy.y + this.dCard.y + 2 * this.dGapBetweenComponents.y + optionLines * this.textHeight;
		this.cDiscardPilePlayer = new NumbersPair(x, y);

		x = this.cDeckEnemy.x;
		y = this.cDiscardPilePlayer.y;
		this.cDeckPlayer = new NumbersPair(x, y);

		x = this.cBoardEnemy.x;
		y = this.cDiscardPilePlayer.y;
		this.cBoardPlayer = new NumbersPair(x, y);

		x = this.cDeckEnemy.x + this.dCard.x + this.dGapBetweenComponents.x;
		y = this.cDeckEnemy.y + this.dCard.y + this.dGapBetweenComponents.x;
		this.cTextPanel = new NumbersPair(x, y);

	}

}
