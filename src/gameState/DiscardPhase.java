package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import utils.Flow;
import utils.ImageViewAble;
import utils.SelectImageViewManager;

public class DiscardPhase extends AGameState {

	@Override
	public void executeGameState() {

		if (Lists.INSTANCE.boardPlayer.getArrayList().isEmpty())
			Flow.INSTANCE.proceed();

		else {

			EText.DISCARD_PHASE.show();
			EText.CHOOSE_CARDS_TO_DISCARD.show();
			EText.CONTINUE.show();

		}

	}

	@Override
	protected void handleCardPressedBoardPlayer(Card card) {
		SelectImageViewManager.INSTANCE.reverseSelectImageViewAble(card);
	}

	@Override
	protected void executeTextOption(EText eText) {

		for (ImageViewAble imageViewAble : SelectImageViewManager.INSTANCE.getSelectedImageViewAbles()) {

			Card card = (Card) imageViewAble;
			Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(card);
			Lists.INSTANCE.boardPlayer.getArrayList().remove(card);

		}

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Lists.INSTANCE.discardPilePlayer.relocateImageViews();
		Lists.INSTANCE.boardPlayer.relocateImageViews();

		Lists.INSTANCE.discardPilePlayer.toFrontFirstImageView();

		Flow.INSTANCE.proceed();

	}

}
