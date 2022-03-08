package domain;

import java.util.ArrayList;
import java.util.List;

public class Gamer {
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Deck deck) {
		cards.add(deck.distributeCard());
	}

	public List<Card> getCards() {
		return this.cards;
	}
}
