package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import javafx.scene.input.KeyCode;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.Text;

public abstract class AGameState {

	public abstract void executeGameState();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text option executing");
		Logger.INSTANCE.logNewLine(textEnum);

		Text.INSTANCE.concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		int textOptionToHandle = KeyCodeHandler.INSTANCE.getKeyCodeInt(keyCode);

		EText textEnumPressed = Text.INSTANCE.getTextEnumOptionShowing(textOptionToHandle);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void executeTextOption(EText eText) {

	}

	protected final void concealText() {
		Text.INSTANCE.concealText();
	}

	public final void handleCardPressed(Card card) {

		if (Lists.INSTANCE.boardEnemy.getArrayList().contains(card))
			handleCardPressedBoardEnemy(card);
		else if (Lists.INSTANCE.boardPlayer.getArrayList().contains(card))
			handleCardPressedBoardPlayer(card);

	}

	protected void handleCardPressedBoardEnemy(Card card) {

	}

	protected void handleCardPressedBoardPlayer(Card card) {

	}

}
