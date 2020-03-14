package domain.card;

import domain.card.cardfactory.Card;
import domain.card.cardfactory.CardFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
	private static final int ONE_COUNT = 1;

	private final Queue<Card> cards;

	public CardDeck() {
		List<Card> cards = CardFactory.create();
		this.cards = new LinkedList<>(cards);
	}

	public List<Card> draw(int count) {
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			cards.add(this.cards.poll());
		}
		return cards;
	}

	public List<Card> draw() {
		return draw(ONE_COUNT);
	}
}
