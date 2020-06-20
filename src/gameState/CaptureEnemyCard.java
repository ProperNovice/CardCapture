package gameState;

import card.Card;
import controller.Lists;
import controller.Modifiers;
import enums.EText;
import enums.EValue;
import utils.ArrayList;
import utils.Flow;
import utils.SelectImageViewManager;

public class CaptureEnemyCard extends AGameState {

	private Card selectedEnemyCard = null;
	private ArrayList<Card> selectedPlayerCards = new ArrayList<Card>();

	@Override
	public void executeGameState() {

		this.selectedEnemyCard = null;
		this.selectedPlayerCards.clear();

		if (Modifiers.INSTANCE.captureEnemyCards.size() == 1)
			handleCardPressedBoardEnemy(Modifiers.INSTANCE.captureEnemyCards.getFirst());

		handleGameState();

	}

	@Override
	protected void executeTextOption(EText eText) {

		SelectImageViewManager.INSTANCE.releaseSelectImageViews();

		for (Card card : this.selectedPlayerCards) {

			Lists.INSTANCE.boardPlayer.getArrayList().remove(card);
			Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(card);

		}
		
		Lists.INSTANCE.boardEnemy.getArrayList().remove(this.selectedEnemyCard);
		Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(this.selectedEnemyCard);

		Lists.INSTANCE.discardPilePlayer.relocateImageViews();
		Lists.INSTANCE.discardPilePlayer.toFrontFirstImageView();
		Lists.INSTANCE.boardEnemy.relocateImageViews();
		Lists.INSTANCE.boardPlayer.relocateImageViews();

		Flow.INSTANCE.proceed();

	}

	@Override
	protected void handleCardPressedBoardEnemy(Card card) {

		if (card.equals(this.selectedEnemyCard))
			return;

		if (!Modifiers.INSTANCE.captureEnemyCards.contains(card))
			return;

		if (this.selectedEnemyCard != null)
			SelectImageViewManager.INSTANCE.releaseSelectImageViewAble(this.selectedEnemyCard);

		this.selectedEnemyCard = card;
		SelectImageViewManager.INSTANCE.addSelectImageViewAble(this.selectedEnemyCard);

		handleGameState();

	}

	@Override
	protected void handleCardPressedBoardPlayer(Card card) {

		if (this.selectedPlayerCards.contains(card)) {

			this.selectedPlayerCards.remove(card);
			SelectImageViewManager.INSTANCE.releaseSelectImageViewAble(card);

		} else if (this.selectedEnemyCard == null || card.getEValue().equals(EValue.JOKER)
				|| card.getESuit().equals(this.selectedEnemyCard.getESuit())) {

			this.selectedPlayerCards.addLast(card);
			SelectImageViewManager.INSTANCE.addSelectImageViewAble(card);

		}

		handleGameState();

	}

	private void handleGameState() {

		handleText();
		handleCardsSelected();

	}

	private void handleCardsSelected() {

		if (this.selectedEnemyCard == null)
			return;

		for (Card card : this.selectedPlayerCards.clone()) {

			if (card.getEValue().equals(EValue.JOKER))
				continue;

			if (card.getESuit().equals(this.selectedEnemyCard.getESuit()))
				continue;

			this.selectedPlayerCards.remove(card);
			SelectImageViewManager.INSTANCE.releaseSelectImageViewAble(card);

		}

	}

	private void handleText() {

		concealText();
		EText.CAPTURE_ENEMY_CARD_INDICATOR.show();

		if (this.selectedEnemyCard == null)
			return;

		int highestSuitValue = 0, jokers = 0, totalPointsValue = 0;

		for (Card card : this.selectedPlayerCards) {

			if (card.getEValue().equals(EValue.JOKER)) {
				jokers++;
				continue;
			}

			if (!card.getESuit().equals(this.selectedEnemyCard.getESuit()))
				continue;

			int cardPointValue = card.getEValue().getPointValue();

			totalPointsValue += cardPointValue;

			if (highestSuitValue < cardPointValue)
				highestSuitValue = cardPointValue;

		}

		totalPointsValue += jokers * highestSuitValue;

		if (totalPointsValue < this.selectedEnemyCard.getEValue().getPointValue())
			return;

		EText.CONTINUE.show();

	}

}
