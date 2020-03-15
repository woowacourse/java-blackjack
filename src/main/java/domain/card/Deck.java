package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	private final Stack<Card> deck;

	private Deck(Stack<Card> deck) {
		this.deck = deck;
	}

	public static Deck of(List<Card> cards) {
		Stack<Card> deck = new Stack<>();
		cards.forEach(deck::push);
		Collections.shuffle(deck);
		return new Deck(deck);
	}

	public Card pop() {
		return deck.pop();
	}
}
