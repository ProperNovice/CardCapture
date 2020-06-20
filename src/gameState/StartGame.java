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
		addCardsToDiscardPilePlayer();

//		Flow.INSTANCE.getFlow().addLast(EnemyPhase.class);
//		Flow.INSTANCE.getFlow().addLast(DiscardPhase.class);
//		Flow.INSTANCE.getFlow().addLast(DrawPhase.class);
		Flow.INSTANCE.getFlow().addLast(CapturePhase.class);

		Flow.INSTANCE.getFlow().addLast(RestartGame.class);
		Flow.INSTANCE.proceed();

	}

	protected void addCardsToBoardEnemy() {

		ListImageViewAbles<Card> list = Lists.INSTANCE.boardEnemy;

		list.getArrayList().addLast(new Card(EValue.TEN, ESuit.HEARTS));
		list.getArrayList().addLast(new Card(EValue.FIVE, ESuit.HEARTS));
		list.getArrayList().addLast(new Card(EValue.KING, ESuit.CLUBS));
		list.getArrayList().addLast(new Card(EValue.SIX, ESuit.DIAMONDS));

		list.relocateImageViews();

		for (Card card : list)
			card.getImageView().flip();

	}

	protected void addCardsToBoardPlayer() {

		ListImageViewAbles<Card> list = Lists.INSTANCE.boardPlayer;

		list.getArrayList().addLast(new Card(EValue.SIX, ESuit.CLUBS));
		list.getArrayList().addLast(new Card(EValue.THREE, ESuit.DIAMONDS));
//		list.getArrayList().addLast(new Card(EValue.ACE, ESuit.SPADES));
		list.getArrayList().addLast(new Card(EValue.QUEEN, ESuit.HEARTS));
//		list.getArrayList().addLast(new Card(EValue.JOKER, ESuit.RED));
		list.getArrayList().addLast(new Card(EValue.JOKER, ESuit.BLACK));

		list.relocateImageViews();

		for (Card card : list)
			card.getImageView().flip();

	}

	protected void addCardsToDiscardPilePlayer() {

		ListImageViewAbles<Card> list = Lists.INSTANCE.discardPilePlayer;

		list.getArrayList().addLast(new Card(EValue.TWO, ESuit.HEARTS));
		list.getArrayList().addLast(new Card(EValue.TWO, ESuit.SPADES));
		list.getArrayList().addLast(new Card(EValue.FOUR, ESuit.DIAMONDS));
		list.getArrayList().addLast(new Card(EValue.QUEEN, ESuit.DIAMONDS));
		list.getArrayList().addLast(new Card(EValue.JOKER, ESuit.BLACK));

		list.relocateImageViews();

		for (Card card : list)
			card.getImageView().flip();

	}

}
