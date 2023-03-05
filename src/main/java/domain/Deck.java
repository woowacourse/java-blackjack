package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
	private static final Queue<Card> deck;

	static {
		deck = new LinkedList<>(makeDeck());
	}

	private Deck() {
	}

	private static List<Card> makeDeck() {
		List<Card> cards = new ArrayList<>();
		for (Denomination denomination : Denomination.values()) {
			makeCard(cards, denomination);
		}
		Collections.shuffle(cards);
		return cards;
	}

	private static void makeCard(List<Card> cards, Denomination denomination) {
		for (Suits suit : Suits.values()) {
			cards.add(new Card(denomination, suit));
		}
	}

	public static Card pickCard() {
		return deck.poll();
	}

	public static Queue<Card> getDeck() {
		return new LinkedList<>(deck);
	}
}
