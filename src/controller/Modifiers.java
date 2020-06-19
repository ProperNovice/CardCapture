package controller;

import utils.ISaveLoadStateAble;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	private Modifiers() {
		saveStart();
	}

	@Override
	public void saveStart() {
		loadStart();
	}

	@Override
	public void loadStart() {

	}

	@Override
	public void saveState() {

	}

	@Override
	public void loadState() {

	}

}
