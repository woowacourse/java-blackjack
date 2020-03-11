package com.blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
	private Stack<Card> cards;

	private CardDeck(Stack<Card> cards) {
		this.cards = cards;
	}

	public static CardDeck create() {
		Stack<Card> cards = new Stack<>();
		cards.addAll(CardFactory.create());
		Collections.shuffle(cards);
		return new CardDeck(cards);
	}

	public Card pop() {
		return cards.pop();
	}
}
