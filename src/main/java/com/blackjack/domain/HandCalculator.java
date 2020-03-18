package com.blackjack.domain;

import java.util.List;

import com.blackjack.domain.card.Card;

public class HandCalculator {
	private static final int ACE_UPWARD_CONDITION = 11;
	private static final int ACE_UPWARD_SCORE = 10;

	public static int calculate(List<Card> cards) {
		int totalScore = sumAllCard(cards);
		return computeAceBy(totalScore, hasAce(cards));
	}

	private static int sumAllCard(List<Card> cards) {
		return cards.stream()
				.mapToInt(Card::getNumber)
				.sum();
	}

	private static int computeAceBy(int totalScore, boolean hasAce) {
		if (totalScore <= ACE_UPWARD_CONDITION && hasAce) {
			totalScore += ACE_UPWARD_SCORE;
		}
		return totalScore;
	}

	private static boolean hasAce(List<Card> cards) {
		return cards.stream().anyMatch(Card::isAce);
	}
}
