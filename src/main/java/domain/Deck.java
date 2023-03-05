package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private static final List<Card> deck;
	private static int index = 0;

	static {
		deck = makeDeck();
		Collections.shuffle(deck);
	}

	private Deck() {
	}

	private static List<Card> makeDeck() {
		List<Card> cards = new ArrayList<>();
		for (Denomination denomination : Denomination.values()) {
			makeCard(cards, denomination);
		}
		return cards;
	}

	private static void makeCard(List<Card> cards, Denomination denomination) {
		for (Suits suit : Suits.values()) {
			cards.add(new Card(denomination, suit));
		}
	}

	public static Card pickCard() {
		return deck.get(index++);
	}

	public static List<Card> getDeck() {
		return new ArrayList<>(deck);
	}
}
