package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private static final int TOP = 0;

	private List<Card> cards;

	public Deck() {
		this.cards = refill(CardRepository.cards());
	}

	private List<Card> refill(List<Card> cards) {
		List<Card> refillCards = new ArrayList<>(cards);
		Collections.shuffle(refillCards);
		return refillCards;
	}

	public Card draw() {
		if (cards.isEmpty()) {
			cards = refill(CardRepository.cards());
		}
		return cards.remove(TOP);
	}
}
