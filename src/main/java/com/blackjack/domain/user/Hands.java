package com.blackjack.domain.user;

import static com.blackjack.domain.Score.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.List;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;

public class Hands {
	private static final int ACE_UPWARD_CONDITION = 11;
	private static final int ACE_UPWARD_SCORE = 10;

	private List<Card> cards;

	public Hands(List<Card> cards) {
		this.cards = new ArrayList<>(cards);
	}

	void add(Card card) {
		cards.add(card);
	}

	public Score calculateScore() {
		int totalScore = cards.stream()
				.mapToInt(Card::getScore)
				.sum();
		totalScore = computeBustBy(totalScore);
		totalScore = computeAceBy(totalScore);
		return Score.valueOf(totalScore);
	}

	private int computeBustBy(int totalScore) {
		if (totalScore > BLACKJACK_SCORE) {
			totalScore = 0;
		}
		return totalScore;
	}

	private int computeAceBy(int totalScore) {
		if (totalScore <= ACE_UPWARD_CONDITION && hasAce()) {
			totalScore += ACE_UPWARD_SCORE;
		}
		return totalScore;
	}

	private boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	public List<Card> getCards() {
		return cards;
	}
}
