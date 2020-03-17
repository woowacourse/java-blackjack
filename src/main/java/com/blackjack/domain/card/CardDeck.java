package com.blackjack.domain.card;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.blackjack.exception.EmptyCardDeckException;

public class CardDeck {
	private Stack<Card> cards;

	private CardDeck(Stack<Card> cards) {
		validateDuplicate(cards);
		this.cards = cards;
	}

	public static CardDeck create(List<Card> cards) {
		Stack<Card> deck = new Stack<>();
		deck.addAll(cards);
		Collections.shuffle(deck);
		return new CardDeck(deck);
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
		try {
			return cards.pop();
		} catch (EmptyStackException e) {
			throw new EmptyCardDeckException();
		}
	}
}
