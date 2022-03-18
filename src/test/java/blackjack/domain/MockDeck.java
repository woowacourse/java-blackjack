package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.DeckStrategy;

public class MockDeck implements DeckStrategy {

	private Deque<Card> cards;

	public MockDeck(List<Card> cards) {
		this.cards = new ArrayDeque<>(cards);
	}

	@Override
	public Card distributeCard() {
		return this.cards.removeLast();
	}
}
