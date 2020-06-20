package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import utils.ArrayList;
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

		EText.SACRIFICE_TWO_CARDS_INDICATOR.showText();

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

	private void handleText() {

		if (this.cardEnemySelected == null)
			return;

		if (this.cardsPlayerSelected.size() != 2)
			return;

		EText.CONTINUE.showText();

	}

}
