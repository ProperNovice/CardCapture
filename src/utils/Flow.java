package utils;

import gameState.AGameState;

public enum Flow {

	INSTANCE;

	private AGameState currentGameState = null;
	private ArrayList<Class<? extends AGameState>> flow = new ArrayList<>();

	private Flow() {

	}

	public void proceed() {

		this.currentGameState = GameStatePool.INSTANCE.acquire(this.flow.removeFirst());
		executeGameState();

	}

	public void executeGameState(Class<? extends AGameState> gameStateClass) {

		this.currentGameState = GameStatePool.INSTANCE.acquire(gameStateClass);
		executeGameState();

	}

	private void executeGameState() {

		Logger.INSTANCE.log("executing gamestate");
		Logger.INSTANCE.logNewLine(this.currentGameState.getClass());

		this.currentGameState.executeGameState();

	}

	public ArrayList<Class<? extends AGameState>> getFlow() {
		return this.flow;
	}

	public AGameState getCurrentGameState() {
		return this.currentGameState;
	}

	public void print() {
		Logger.INSTANCE.logClass(this);
		this.flow.print();
	}

}
