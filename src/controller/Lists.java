package controller;

import card.Card;
import enums.ESuit;
import enums.EValue;
import utils.ArrayList;
import utils.CoordinatesListBuilder;
import utils.DirectionEnum;
import utils.ListImageViewAbles;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Lists {

	INSTANCE;

	public ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();
	public ListImageViewAbles<Card> discardPileEnemy, discardPilePlayer, deckEnemy, deckPlayer, boardEnemy, boardPlayer;

	public void instantiate() {

		createLists();
		repleteLists();
		saveLists();
		resetLists();

		Logger.INSTANCE.logNewLine("lists instantiated -> " + this.lists.size());

	}

	public void resetLists() {

		for (ArrayList<? extends Object> list : this.lists)
			list.loadStart();

	}

	private void createLists() {

		this.discardPileEnemy = new ListImageViewAbles<Card>(
				new CoordinatesListBuilder().coordinatesNumbersPair(Credentials.INSTANCE.cDiscardPileEnemy)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		this.discardPilePlayer = new ListImageViewAbles<Card>(
				new CoordinatesListBuilder().coordinatesNumbersPair(Credentials.INSTANCE.cDiscardPilePlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		this.deckEnemy = new ListImageViewAbles<Card>(
				new CoordinatesListBuilder().coordinatesNumbersPair(Credentials.INSTANCE.cDeckEnemy)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		this.deckPlayer = new ListImageViewAbles<Card>(
				new CoordinatesListBuilder().coordinatesNumbersPair(Credentials.INSTANCE.cDeckPlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		this.boardEnemy = new ListImageViewAbles<Card>(
				new CoordinatesListBuilder().coordinatesNumbersPair(Credentials.INSTANCE.cBoardEnemy)
						.directionEnumHorizontal(DirectionEnum.LEFT).build(),
				4);

		this.boardPlayer = new ListImageViewAbles<Card>(
				new CoordinatesListBuilder().coordinatesNumbersPair(Credentials.INSTANCE.cBoardPlayer)
						.directionEnumHorizontal(DirectionEnum.LEFT).build(),
				4);

	}

	private void saveLists() {

		for (ArrayList<? extends Object> list : this.lists)
			list.saveStart();

	}

	private void repleteLists() {

		ArrayList<ESuit> suits = new ArrayList<ESuit>(ESuit.values());
		suits.remove(ESuit.BLACK);
		suits.remove(ESuit.RED);

		ArrayList<EValue> values = new ArrayList<EValue>(EValue.values());
		values.remove(EValue.JOKER);

		ArrayList<Card> cards = new ArrayList<Card>();

		for (ESuit eSuit : suits)
			for (EValue eValue : values)
				cards.addLast(new Card(eValue, eSuit));

		cards.addLast(new Card(EValue.JOKER, ESuit.BLACK));
		cards.addLast(new Card(EValue.JOKER, ESuit.RED));
		Modifiers.INSTANCE.cardOrder.addAll(cards);

		ListImageViewAbles<Card> list = null;

		for (Card card : cards) {

			EValue eValue = card.getEValue();

			switch (eValue) {

			case TWO:
				list = this.deckPlayer;
				break;

			case THREE:
				list = this.deckPlayer;
				break;

			case FOUR:
				list = this.deckPlayer;
				break;

			case JOKER:
				list = this.deckPlayer;
				break;

			default:
				list = this.deckEnemy;
				break;

			}

			list.getArrayList().addLast(card);

		}

		this.deckEnemy.getArrayList().shuffle();
		this.deckPlayer.getArrayList().shuffle();

		this.deckEnemy.toFrontFirstImageView();
		this.deckPlayer.toFrontFirstImageView();

		this.deckEnemy.relocateImageViews();
		this.deckPlayer.relocateImageViews();

	}

}
