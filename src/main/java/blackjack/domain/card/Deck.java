package blackjack.domain.card;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import blackjack.domain.exceptions.InvalidDeckException;

public class Deck {
	private static final int TOP = 0;

	private final List<Card> cards;

	public Deck(List<Card> cards) {
		validate(cards);
		Collections.shuffle(cards);
		this.cards = cards;
	}

	private void validate(List<Card> cards) {
		validateEmpty(cards);
		validateDuplication(cards);
	}

	private void validateEmpty(List<Card> cards) {
		if (Objects.isNull(cards) || cards.isEmpty()) {
			throw new InvalidDeckException(InvalidDeckException.EMPTY);
		}

	}

	private void validateDuplication(List<Card> cards) {
		long distinctCardSize = new HashSet<>(cards).size();

		if (cards.size() != distinctCardSize) {
			throw new InvalidDeckException(InvalidDeckException.DUPLICATE);
		}
	}

	public Card draw() {
		if (cards.isEmpty()) {
			throw new InvalidDeckException(InvalidDeckException.DECK_EMPTY);
		}
		return cards.remove(TOP);
	}
}
