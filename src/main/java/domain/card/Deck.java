package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {
	private Queue<Card> deck;

	public Deck(List<Card> deck) {
		Collections.shuffle(deck);
		this.deck = new LinkedList<>(deck);
	}

	public List<Card> popCard(int number) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			cards.add(deck.poll());
		}
		return cards;
	}
}
