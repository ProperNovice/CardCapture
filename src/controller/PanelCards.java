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
	public void showPanelPlayerDeck() {
		showPanel(Lists.INSTANCE.deckPlayer);
	}

	@SuppressWarnings("unchecked")
	public void showPanelPlayerDiscardPile() {
		showPanel(Lists.INSTANCE.discardPilePlayer);
	}

	@SuppressWarnings("unchecked")
	public void showPanelEnemyDiscardPile() {
		showPanel(Lists.INSTANCE.discardPileEnemy);
	}

	@SuppressWarnings("unchecked")
	public void showPanelEnemy() {
		showPanel(Lists.INSTANCE.deckEnemy);
	}

	private void showPanel(@SuppressWarnings("unchecked") ListImageViewAbles<Card>... lists) {

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

	public void hidePanel() {

		for (Card card : this.panel)
			card.getImageView().setVisible(false);

		this.panel.getArrayList().clear();
		this.panelBackground.getImageView().setVisible(false);

	}

	public boolean contains(Card card) {
		return this.panel.getArrayList().contains(card);
	}

}
