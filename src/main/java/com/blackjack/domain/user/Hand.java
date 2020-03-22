package com.blackjack.domain.user;

import static com.blackjack.domain.GameTable.FIRST_DRAW_COUNT;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.blackjack.domain.Score;
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

	public Score calculateScore() {
		return Score.of(sumAllCards(), hasAce(), isFirstDraw());
	}

	private int sumAllCards() {
		return cards.stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	private boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	private boolean isFirstDraw() {
		return cards.size() == FIRST_DRAW_COUNT;
	}

	public List<Card> getCards() {
		return cards;
	}
}