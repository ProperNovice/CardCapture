package controller;

import utils.ArrayList;
import utils.Logger;

public enum Lists {

	INSTANCE;

	public ArrayList<ArrayList<? extends Object>> lists = new ArrayList<ArrayList<? extends Object>>();

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

	}

	private void saveLists() {

		for (ArrayList<? extends Object> list : this.lists)
			list.saveStart();

	}

	private void repleteLists() {

	}

}
