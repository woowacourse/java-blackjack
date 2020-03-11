package domain.gamer;

import domain.card.Card;

public class Dealer extends Gamer {
	private static final int DRAW_CARD_PIVOT = 16;

	public Dealer() {
		super("딜러");
	}

	public boolean isDrawable() {
		int sum = super.getCards()
			.stream()
			.mapToInt(Card::getCardNumber)
			.sum();
		return sum <= DRAW_CARD_PIVOT;
	}
}
