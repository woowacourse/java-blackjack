package blackjack.domain.card;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Deck {
	private static final int TOP = 0;

	private final List<Card> cards;

	public Deck(List<Card> cards) {
		validate(cards);
		Collections.shuffle(cards);
		this.cards = cards;
	}

	private void validate(List<Card> cards) {
		long distinctCardSize = new HashSet<>(cards).size();

		if (cards.size() != distinctCardSize) {
			throw new InvalidDeckException(InvalidDeckException.INVALID);
		}
	}

	public Card draw() {
		if (cards.isEmpty()) {
			throw new InvalidDeckException(InvalidDeckException.DECK_EMPTY);
		}
		return cards.remove(TOP);
	}
}
