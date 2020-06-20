package gameState;

import enums.EText;
import utils.Flow;

public abstract class EndGame extends AGameState {

	@Override
	public void executeGameState() {

		concealText();

		getEText().show();
		EText.RESTART.show();

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.executeGameState(RestartGame.class);
	}

	protected abstract EText getEText();

}
