package gameState;

import card.Card;
import controller.Lists;
import enums.EText;
import enums.EValue;
import utils.Flow;

public class EnemyPhase extends AGameState {

	@Override
	public void executeGameState() {

		if (Lists.INSTANCE.deckEnemy.getArrayList().isEmpty())
			Flow.INSTANCE.proceed();

		else {

			EText.ENEMY_PHASE.show();
			EText.CONTINUE.show();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		if (Lists.INSTANCE.boardEnemy.getArrayList().isEmpty())
			handleBoardEnemyEmpty();
		else
			handleBoardEnemyNonEmpty();

	}

	private void handleBoardEnemyEmpty() {

		while (!Lists.INSTANCE.boardEnemy.getArrayList().isMaxedCapacity()) {

			Card card = Lists.INSTANCE.deckEnemy.getArrayList().getRandom();

			if (card.getEValue().getPointValue() >= EValue.JACK.getPointValue())
				continue;

			Lists.INSTANCE.boardEnemy.getArrayList().addLast(card);
			Lists.INSTANCE.deckEnemy.getArrayList().remove(card);
			card.getImageView().flip();

		}

		Lists.INSTANCE.boardEnemy.relocateImageViews();
		Flow.INSTANCE.proceed();

	}

	private void handleBoardEnemyNonEmpty() {

		Card card = Lists.INSTANCE.deckEnemy.getArrayList().removeFirst();
		Lists.INSTANCE.boardEnemy.getArrayList().addLast(card);
		card.getImageView().flip();

		Lists.INSTANCE.boardEnemy.relocateImageViews();
		Flow.INSTANCE.proceed();

	}

}
