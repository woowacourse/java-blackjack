package com.blackjack.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;

public class Hands {
	private static final int ACE_UPWARD_CONDITION = 11;
	private static final int ACE_UPWARD_SCORE = 10;
	private static final int INITIAL_HANDS_COUNT = 2;

	private List<Card> cards;

	public Hands(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	void add(Card card) {
		cards.add(card);
	}

	Score calculateScore() {
		int totalScore = cards.stream()
			.mapToInt(Card::getScore)
			.sum();

		totalScore = computeAceBy(totalScore);
		return new Score(totalScore, isInitialHands());
	}

	private boolean isInitialHands() {
		return cards.size() == INITIAL_HANDS_COUNT;
	}

	private int computeAceBy(int totalScore) {
		if (totalScore <= ACE_UPWARD_CONDITION && hasAce()) {
			totalScore += ACE_UPWARD_SCORE;
		}
		return totalScore;
	}

	private boolean hasAce() {
		return cards.stream().anyMatch(Card::isAce);
	}
}
