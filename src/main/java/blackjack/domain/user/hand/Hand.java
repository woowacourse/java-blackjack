package blackjack.domain.user.hand;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.card.Card;

public class Hand {
	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void add(Card card) {
		cards.add(card);
	}

	public void add(List<Card> cards) {
		this.cards.addAll(cards);
	}
}
