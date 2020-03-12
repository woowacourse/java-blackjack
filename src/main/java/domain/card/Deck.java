package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	private final Stack<Card> deck;

	public Deck(List<Card> cards) {
		deck = new Stack<>();
		cards.forEach(deck::push);
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public Card pop() {
		return deck.pop();
	}
}
