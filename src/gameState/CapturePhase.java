package gameState;

import card.Card;
import controller.Lists;
import controller.Modifiers;
import enums.ESuit;
import enums.EText;
import enums.EValue;
import utils.ArrayList;
import utils.Flow;
import utils.HashMap;

public class CapturePhase extends AGameState {

	private boolean gameLost = true;

	@Override
	public void executeGameState() {

		this.gameLost = true;

		EText.CAPTURE_PHASE.showText();

		handleCaptureEnemyCard();
		handleLetYourCardGetCaptured();
		handleSacrificeTwoCards();

		if (!this.gameLost)
			return;

		Flow.INSTANCE.executeGameState(EndGameLost.class);

	}

	@Override
	protected void executeTextOption(EText eText) {

		Class<? extends AGameState> gameState = null;

		if (eText.equals(EText.CAPTURE_ENEMY_CARD_OPTION))
			gameState = CaptureEnemyCard.class;

		else if (eText.equals(EText.LET_YOUR_CARD_GET_CAPTURED))
			gameState = LetYourCardGetCaptured.class;

		else if (eText.equals(EText.SACRIFICE_TWO_CARDS))
			gameState = SacrificeTwoCards.class;

		Flow.INSTANCE.executeGameState(gameState);

	}

	private void handleCaptureEnemyCard() {

		ArrayList<ESuit> listESuits = new ArrayList<ESuit>();
		listESuits.addLast(ESuit.CLUBS);
		listESuits.addLast(ESuit.DIAMONDS);
		listESuits.addLast(ESuit.HEARTS);
		listESuits.addLast(ESuit.SPADES);

		HashMap<ESuit, Integer> totalPointsValues = new HashMap<ESuit, Integer>();
		HashMap<ESuit, Integer> highestPointValues = new HashMap<ESuit, Integer>();
		int jokers = 0;

		for (ESuit eSuit : listESuits) {

			totalPointsValues.put(eSuit, 0);
			highestPointValues.put(eSuit, 0);
		}

		for (Card card : Lists.INSTANCE.boardPlayer) {

			if (card.getEValue().equals(EValue.JOKER)) {
				jokers++;
				continue;
			}

			EValue eValue = card.getEValue();
			ESuit eSuit = card.getESuit();
			int pointValue = eValue.getPointValue();

			totalPointsValues.put(eSuit, totalPointsValues.getValue(eSuit) + pointValue);

			if (highestPointValues.getValue(eSuit) < pointValue)
				highestPointValues.put(eSuit, pointValue);

		}

		for (ESuit eSuit : listESuits)
			totalPointsValues.put(eSuit,
					totalPointsValues.getValue(eSuit) + jokers * highestPointValues.getValue(eSuit));

		System.out.println("total points values");
		totalPointsValues.print();

		for (Card card : Lists.INSTANCE.boardEnemy)
			if (card.getEValue().getPointValue() <= totalPointsValues.getValue(card.getESuit()))
				Modifiers.INSTANCE.captureEnemyCards.addLast(card);

		if (Modifiers.INSTANCE.captureEnemyCards.isEmpty())
			return;

		this.gameLost = false;
		EText.CAPTURE_ENEMY_CARD_OPTION.showText();

	}

	private void handleLetYourCardGetCaptured() {

		if (cardsCanBeDiscardedFromBoardPlayer() == 0)
			return;

		if (Lists.INSTANCE.boardEnemy.getArrayList().getFirst().getEValue().getPointValue() >= 11)
			return;

		this.gameLost = false;
		EText.LET_YOUR_CARD_GET_CAPTURED.showText();

	}

	private void handleSacrificeTwoCards() {

		if (cardsCanBeDiscardedFromBoardPlayer() < 2)
			return;

		this.gameLost = false;
		EText.SACRIFICE_TWO_CARDS.showText();

	}

	private int cardsCanBeDiscardedFromBoardPlayer() {

		int cards = 0;

		for (Card card : Lists.INSTANCE.boardPlayer)
			if (card.getEValue().getPointValue() < EValue.JACK.getPointValue())
				cards++;

		return cards;

	}

}
