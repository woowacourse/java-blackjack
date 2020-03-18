package com.blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
	private static final int CARD_DECK_SIZE = 52;

	private Stack<Card> cards;

	private CardDeck(Stack<Card> cards) {
		this.cards = cards;
	}

	public static CardDeck create() {
		Stack<Card> cards = new Stack<>();
		cards.addAll(CardFactory.create());
		validateSize(cards);
		Collections.shuffle(cards);
		return new CardDeck(cards);
	}

	private static void validateSize(Stack<Card> cards) {
		if (cards.size() != CARD_DECK_SIZE) {
			throw new IllegalArgumentException("카드 덱의 카드 수가 올바르지 않습니다.");
		}
	}

	public Card pop() {
		if (cards.isEmpty()) {
			throw new IllegalArgumentException("카드를 모두 사용했습니다.");
		}
		return cards.pop();
	}
}
