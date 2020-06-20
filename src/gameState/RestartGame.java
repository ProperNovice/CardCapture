package gameState;

import card.Card;
import controller.Lists;
import controller.Modifiers;
import utils.Flow;
import utils.ListImageViewAbles;

public class RestartGame extends AGameState {

	@Override
	public void executeGameState() {

		Modifiers.INSTANCE.loadStart();

		Flow.INSTANCE.getFlow().clear();
		Flow.INSTANCE.getFlow().loadStart();

		Lists.INSTANCE.resetLists();
		handleDeck(Lists.INSTANCE.deckEnemy);
		handleDeck(Lists.INSTANCE.deckPlayer);

		Flow.INSTANCE.proceed();

	}

	private void handleDeck(ListImageViewAbles<Card> list) {

		for (Card card : list)
			card.getImageView().flipBack();

		list.getArrayList().shuffle();
		list.toFrontFirstImageView();
		list.relocateImageViews();

	}

}
