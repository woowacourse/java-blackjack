package domain.card;

import exception.EmptyDeckException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {
	private static final int FIRST_CARD = 0;
	private static final int ZERO = 0;

	private final List<Card> deck;

	public Deck() {
		this.deck = new LinkedList<>(CardFactory.create());
		Collections.shuffle(deck);
	}

	public Card drawCard() {
		if (deck.size() == ZERO) {
			throw new EmptyDeckException();
		}
		return deck.remove(FIRST_CARD);
	}

	public List<Card> getDeck() {
		return Collections.unmodifiableList(deck);
	}
}
