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

			EText.ENEMY_PHASE.showText();
			EText.CONTINUE.showText();

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

		boolean add = true;

		while (!Lists.INSTANCE.boardEnemy.getArrayList().isMaxedCapacity()) {

			Card card = Lists.INSTANCE.deckEnemy.getArrayList().getRandom();
			EValue eValue = card.getEValue();

			switch (eValue) {

			case JACK:
				add = false;
				break;

			case QUEEN:
				add = false;
				break;

			case KING:
				add = false;
				break;

			case ACE:
				add = false;
				break;

			default:
				add = true;
				break;

			}

			if (!add)
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
