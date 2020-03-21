package domain.gamer;

import domain.card.Deck;

public class Dealer extends Gamer {
	private static final int DRAW_CARD_PIVOT = 16;
	private static final String DEALER_NAME = "딜러";

	public Dealer() {
		super(DEALER_NAME, "0");
	}

	@Override
	public boolean isDrawable() {
		return super.calculateScore() <= DRAW_CARD_PIVOT;
	}

	public void addCardAtDealer(Deck deck) {
		while (isDrawable()) {
			addCard(deck.popCard());
		}
	}
}
