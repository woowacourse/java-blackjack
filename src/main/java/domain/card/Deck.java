package domain.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import domain.strategy.ShuffleStrategy;

public class Deck {
	private final Queue<Card> deck;

	public Deck(ShuffleStrategy strategy) {
		List<Card> cards = new ArrayList<>();
		for (Suits suit : Suits.values()) {
			makeCard(cards, suit);
		}
		strategy.shuffle(cards);
		deck = new LinkedList<>(cards);
	}

	private static void makeCard(List<Card> cards, Suits suit) {
		for (Denomination denomination : Denomination.values()) {
			cards.add(new Card(denomination, suit));
		}
	}

	public Card pickCard() {
		return deck.poll();
	}

	public Queue<Card> getDeck() {
		return new LinkedList<>(deck);
	}
}
