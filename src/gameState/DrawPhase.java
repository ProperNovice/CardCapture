package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import utils.Flow;

public class DrawPhase extends AGameState {

	@Override
	public void executeGameState() {

		EText.DRAW_PHASE.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		while (!Lists.INSTANCE.boardPlayer.getArrayList().isMaxedCapacity()) {

			if (Lists.INSTANCE.deckPlayer.getArrayList().isEmpty()) {

				Lists.INSTANCE.deckPlayer.getArrayList().addAll(Lists.INSTANCE.discardPilePlayer.getArrayList());
				Lists.INSTANCE.discardPilePlayer.getArrayList().clear();

				Lists.INSTANCE.deckPlayer.animateSynchronousLock();

				for (Card card : Lists.INSTANCE.deckPlayer)
					card.getImageView().flip();

				Lists.INSTANCE.deckPlayer.getArrayList().shuffle();
				Lists.INSTANCE.deckPlayer.toFrontFirstImageView();

			}
			
			Card card = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
			card.getImageView().flip();
			Lists.INSTANCE.boardPlayer.getArrayList().addLast(card);
			Lists.INSTANCE.boardPlayer.relocateImageViews();

		}
		
		Flow.INSTANCE.proceed();

	}

}
