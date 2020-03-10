package domain.Gamer;

import java.util.List;

import domain.card.Card;

public class Dealer extends Gamer {
	private static final int DRAW_CARD_PIVOT = 16;

	public Dealer(List<Card> cards) {
		super("딜러", cards);
	}

	@Override
	public boolean isDrawable() {
		int sum = super.cards
			.stream()
			.mapToInt(Card::getCardNumber)
			.sum();
		return sum <= DRAW_CARD_PIVOT;
	}
}
