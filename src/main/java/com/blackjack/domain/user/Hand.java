package com.blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.blackjack.domain.HandCalculator;
import com.blackjack.domain.card.Card;

public class Hand {
	private List<Card> cards;

	public Hand(List<Card> cards) {
		Objects.requireNonNull(cards);
		this.cards = new ArrayList<>(cards);
	}

	void add(Card card) {
		cards.add(card);
	}

	public int calculateScore() {
		return HandCalculator.calculate(cards);
	}

	public List<Card> getCards() {
		return cards;
	}
}
