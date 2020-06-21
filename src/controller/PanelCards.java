package controller;

import card.Card;
import model.PanelBackground;
import utils.ArrayList;
import utils.CoordinatesListBuilder;
import utils.ListImageViewAbles;
import utils.RearrangeTypeEnum;

public enum PanelCards {

	INSTANCE;

	private PanelBackground panelBackground = new PanelBackground();
	private ListImageViewAbles<Card> panel = null;

	private PanelCards() {

		this.panel = new ListImageViewAbles<Card>(new CoordinatesListBuilder()
				.coordinatesNumbersPair(Credentials.INSTANCE.cPanelCards).rearrangeTypeEnum(RearrangeTypeEnum.PIVOT)
				.gapX(-Credentials.INSTANCE.dCard.x + Credentials.INSTANCE.gapBetweenCardsPanel).objectsPerRow(14)
				.build());

	}

	@SuppressWarnings("unchecked")
	public void showPanelPlayer() {
		showPanelCards(Lists.INSTANCE.discardPilePlayer, Lists.INSTANCE.deckPlayer, Lists.INSTANCE.boardPlayer);
	}

	@SuppressWarnings("unchecked")
	public void showPanelEnemy() {
		showPanelCards(Lists.INSTANCE.deckEnemy, Lists.INSTANCE.boardEnemy);
	}

	private void showPanelCards(@SuppressWarnings("unchecked") ListImageViewAbles<Card>... lists) {

		this.panel.getArrayList().clear();

		ArrayList<Card> cardsToShow = new ArrayList<Card>();

		for (ListImageViewAbles<Card> list : lists)
			cardsToShow.addAll(list.getArrayList());

		for (Card card : Modifiers.INSTANCE.cardOrder)
			if (cardsToShow.contains(card))
				this.panel.getArrayList().addLast(card.getCardClone());

		for (Card card : this.panel)
			card.getImageView().setVisible(true);

		this.panel.toBackFirstImageView();
		this.panelBackground.getImageView().setVisible(true);
		this.panel.relocateImageViews();

	}

}
