package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void add(Card card) {
		cards.add(card);
	}
}
