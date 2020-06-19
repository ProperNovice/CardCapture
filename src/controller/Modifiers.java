package controller;

import card.Card;
import utils.ArrayList;
import utils.ISaveLoadStateAble;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	public ArrayList<Card> cardOrder = new ArrayList<Card>();
	public ArrayList<Card> captureEnemyCards = new ArrayList<Card>();

	private Modifiers() {
		saveStart();
	}

	@Override
	public void saveStart() {
		loadStart();
	}

	@Override
	public void loadStart() {

		this.captureEnemyCards.clear();

	}

	@Override
	public void saveState() {

	}

	@Override
	public void loadState() {

		this.captureEnemyCards.clear();

	}

}
