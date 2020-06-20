package gameState;

import controller.Lists;
import controller.Modifiers;
import utils.Flow;

public class EndTurn extends AGameState {

	@Override
	public void executeGameState() {

		Modifiers.INSTANCE.loadState();

		if (Lists.INSTANCE.deckEnemy.getArrayList().size() + Lists.INSTANCE.boardEnemy.getArrayList().size() == 0)
			Flow.INSTANCE.executeGameState(EndGameWin.class);

		else
			Flow.INSTANCE.getFlow().loadState();

		Flow.INSTANCE.proceed();

	}

}
