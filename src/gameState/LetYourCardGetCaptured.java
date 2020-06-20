package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import utils.ArrayList;
import utils.Flow;
import utils.SelectImageViewManager;

public class LetYourCardGetCaptured extends AGameState {

	private ArrayList<Card> cardsCanBeCaptured = new ArrayList<Card>();
	private Card cardSelected = null;

	@Override
	public void executeGameState() {

		this.cardsCanBeCaptured.clear();
		this.cardSelected = null;

		EText.LET_YOUR_CARD_GET_CAPTURED_INDICATOR.showText();

		for (Card card : Lists.INSTANCE.boardPlayer)
			if (card.getEValue().getPointValue() < 11)
				this.cardsCanBeCaptured.addLast(card);

		if (this.cardsCanBeCaptured.size() > 1)
			return;

		this.cardSelected = this.cardsCanBeCaptured.getFirst();
		SelectImageViewManager.INSTANCE.addSelectImageViewAble(this.cardSelected);

		EText.CONTINUE.showText();

	}

	@Override
	protected void handleCardPressedBoardPlayer(Card card) {

		if (this.cardSelected != null && this.cardSelected.equals(card))
			return;

		if (card.getEValue().getPointValue() >= 11)
			return;

		if (this.cardSelected != null)
			SelectImageViewManager.INSTANCE.reverseSelectImageViewAble(this.cardSelected);

		this.cardSelected = card;
		SelectImageViewManager.INSTANCE.reverseSelectImageViewAble(this.cardSelected);

		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Lists.INSTANCE.boardPlayer.getArrayList().remove(this.cardSelected);
		Lists.INSTANCE.discardPileEnemy.getArrayList().addFirst(this.cardSelected);

		Lists.INSTANCE.discardPileEnemy.getArrayList().addFirst(Lists.INSTANCE.boardEnemy.getArrayList().removeFirst());

		Lists.INSTANCE.discardPileEnemy.toFrontFirstImageView();

		Lists.INSTANCE.boardPlayer.relocateImageViews();
		Lists.INSTANCE.boardEnemy.relocateImageViews();
		Lists.INSTANCE.discardPileEnemy.relocateImageViews();

		Flow.INSTANCE.proceed();

	}

}
