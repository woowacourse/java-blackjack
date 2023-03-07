package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
	private final Queue<Card> deck;

	public Deck() {
		List<Card> cards = new ArrayList<>();
		for (Denomination denomination : Denomination.values()) {
			makeCard(cards, denomination);
		}
		Collections.shuffle(cards);
		deck = new LinkedList<>(cards);
	}

	private static void makeCard(List<Card> cards, Denomination denomination) {
		for (Suits suit : Suits.values()) {
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
