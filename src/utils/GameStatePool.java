package utils;

import java.lang.reflect.InvocationTargetException;

import gameState.AGameState;

public enum GameStatePool {

	INSTANCE;

	private HashMap<Class<? extends AGameState>, AGameState> map = new HashMap<Class<? extends AGameState>, AGameState>();

	private GameStatePool() {

	}

	public AGameState acquire(Class<? extends AGameState> gameStateClass) {

		handleAvailability(gameStateClass);
		return this.map.getValue(gameStateClass);

	}

	private void handleAvailability(Class<? extends AGameState> gameStateClass) {

		if (this.map.containsKey(gameStateClass))
			return;

		try {

			this.map.put(gameStateClass, gameStateClass.getConstructor().newInstance());
			return;

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

		ShutDown.INSTANCE.execute();

	}

}
