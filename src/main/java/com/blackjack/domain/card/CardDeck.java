package com.blackjack.domain.card;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class CardDeck {
	private static final int CARD_DECK_SIZE = 52;

	private Stack<Card> cards;

	private CardDeck(Stack<Card> cards) {
		validateDuplicate(cards);
		this.cards = cards;
	}

	public static CardDeck create(List<Card> cards) {
		Stack<Card> deck = new Stack<>();
		deck.addAll(cards);
		validateSize(deck);
		Collections.shuffle(deck);
		return new CardDeck(deck);
	}

	private static void validateSize(Stack<Card> deck) {
		if (deck.size() != CARD_DECK_SIZE) {
			throw new IllegalArgumentException("카드 덱의 카드 수가 올바르지 않습니다.");
		}
	}

	public static CardDeck create() {
		return create(Card.values());
	}

	private void validateDuplicate(Stack<Card> cards) {
		Set<Card> distinctCards = new HashSet<>(cards);
		if (distinctCards.size() != cards.size()) {
			throw new IllegalArgumentException("카드에 중복된 수가 있습니다.");
		}
	}

	public Card pop() {
		if (cards.isEmpty()) {
			throw new IllegalArgumentException("카드를 모두 사용했습니다.");
		}
		return cards.pop();
	}
}
