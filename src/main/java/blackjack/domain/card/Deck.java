package blackjack.domain.card;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Deck {
	private LinkedList<Card> cards;

	public Deck() {
		this.cards = refill(CardRepository.cards());
	}

	LinkedList<Card> refill(List<Card> cards) {
		validate(cards);
		LinkedList<Card> refillCards = new LinkedList<>(cards);
		Collections.shuffle(refillCards);
		return refillCards;
	}

	private void validate(List<Card> cards) {
		long distinctCardSize = new HashSet<>(cards).size();

		if (cards.size() != distinctCardSize) {
			throw new InvalidDeckException(InvalidDeckException.INVALID);
		}
	}

	public Card draw() {
		if (cards.isEmpty()) {
			cards = refill(CardRepository.cards());
		}
		return cards.poll();
	}
}
