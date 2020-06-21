package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import utils.ArrayList;
import utils.Flow;
import utils.SelectImageViewManager;

public class SacrificeTwoCards extends AGameState {

	private ArrayList<Card> cardsPlayerCanBeSacrificed = new ArrayList<Card>();
	private ArrayList<Card> cardsPlayerSelected = new ArrayList<Card>();
	private Card cardEnemySelected = null;

	@Override
	public void executeGameState() {

		this.cardsPlayerCanBeSacrificed.clear();
		this.cardsPlayerSelected.clear();
		this.cardEnemySelected = null;

		EText.SACRIFICE_TWO_CARDS_INDICATOR.show();

		for (Card card : Lists.INSTANCE.boardPlayer)
			if (card.getEValue().getPointValue() < 11)
				this.cardsPlayerCanBeSacrificed.addLast(card);

		if (this.cardsPlayerCanBeSacrificed.size() == 2) {

			this.cardsPlayerSelected.addAll(this.cardsPlayerCanBeSacrificed);

			for (Card card : this.cardsPlayerSelected)
				SelectImageViewManager.INSTANCE.addSelectImageViewAble(card);

		}

		if (Lists.INSTANCE.boardEnemy.getArrayList().size() == 1) {

			this.cardEnemySelected = Lists.INSTANCE.boardEnemy.getArrayList().getFirst();
			SelectImageViewManager.INSTANCE.addSelectImageViewAble(this.cardEnemySelected);

		}

		handleText();

	}

	@Override
	protected void handleCardPressedBoardEnemy(Card card) {

		if (this.cardEnemySelected != null && this.cardEnemySelected.equals(card))
			return;

		if (this.cardEnemySelected != null)
			SelectImageViewManager.INSTANCE.releaseSelectImageViewAble(this.cardEnemySelected);

		this.cardEnemySelected = card;
		SelectImageViewManager.INSTANCE.addSelectImageViewAble(this.cardEnemySelected);

		handleText();

	}

	@Override
	protected void handleCardPressedBoardPlayer(Card card) {

		if (!this.cardsPlayerCanBeSacrificed.contains(card))
			return;

		if (this.cardsPlayerSelected.contains(card)) {

			this.cardsPlayerSelected.remove(card);
			SelectImageViewManager.INSTANCE.releaseSelectImageViewAble(card);

		} else {

			this.cardsPlayerSelected.addLast(card);
			SelectImageViewManager.INSTANCE.addSelectImageViewAble(card);

		}

		handleText();

	}

	private void handleText() {

		concealText();

		EText.SACRIFICE_TWO_CARDS_INDICATOR.show();

		if (this.cardEnemySelected == null)
			return;

		if (this.cardsPlayerSelected.size() != 2)
			return;

		EText.CONTINUE.show();

	}

	@Override
	protected void executeTextOption(EText eText) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		Lists.INSTANCE.boardEnemy.getArrayList().remove(this.cardEnemySelected);
		Lists.INSTANCE.deckEnemy.getArrayList().addLast(this.cardEnemySelected);
		this.cardEnemySelected.getImageView().flip();

		for (Card card : this.cardsPlayerSelected) {

			Lists.INSTANCE.boardPlayer.getArrayList().remove(card);
			Lists.INSTANCE.discardPileEnemy.getArrayList().addFirst(card);

		}

		Lists.INSTANCE.boardEnemy.relocateImageViews();
		Lists.INSTANCE.boardPlayer.relocateImageViews();
		Lists.INSTANCE.discardPileEnemy.relocateImageViews();
		Lists.INSTANCE.discardPileEnemy.toFrontFirstImageView();
		Lists.INSTANCE.deckEnemy.relocateImageViews();
		Lists.INSTANCE.deckEnemy.toFrontFirstImageView();

		Flow.INSTANCE.proceed();

	}

}
