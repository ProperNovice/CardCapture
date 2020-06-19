package gameState;

import card.Card;
import controller.Lists;
import enums.ESuit;
import enums.EValue;
import utils.Flow;
import utils.ListImageViewAbles;

public class StartGame extends AGameState {

	@Override
	public void executeGameState() {

		addCardsToBoardEnemy();
		addCardsToBoardPlayer();

//		Flow.INSTANCE.getFlow().addLast(EnemyPhase.class);
		Flow.INSTANCE.getFlow().addLast(DiscardPhase.class);

		Flow.INSTANCE.getFlow().addLast(RestartGame.class);
		Flow.INSTANCE.proceed();

	}

	protected void addCardsToBoardEnemy() {

		ListImageViewAbles<Card> list = Lists.INSTANCE.boardEnemy;

		list.getArrayList().addLast(new Card(EValue.SEVEN, ESuit.HEARTS));
		list.getArrayList().addLast(new Card(EValue.FOUR, ESuit.SPADES));
		list.getArrayList().addLast(new Card(EValue.KING, ESuit.DIAMONDS));
		list.getArrayList().addLast(new Card(EValue.SIX, ESuit.DIAMONDS));

		list.relocateImageViews();

		for (Card card : list)
			card.getImageView().flip();

	}

	protected void addCardsToBoardPlayer() {

		ListImageViewAbles<Card> list = Lists.INSTANCE.boardPlayer;

		list.getArrayList().addLast(new Card(EValue.TWO, ESuit.HEARTS));
		list.getArrayList().addLast(new Card(EValue.TWO, ESuit.SPADES));
//		list.getArrayList().addLast(new Card(EValue.FOUR, ESuit.DIAMONDS));
		list.getArrayList().addLast(new Card(EValue.JOKER, ESuit.RED));

		list.relocateImageViews();

		for (Card card : list)
			card.getImageView().flip();

	}

}
