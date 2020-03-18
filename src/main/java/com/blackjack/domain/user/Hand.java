package com.blackjack.domain.user;

import static com.blackjack.domain.GameTable.FIRST_DRAW_COUNT;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.blackjack.domain.Score;
import com.blackjack.domain.card.Card;

public class Hand {
	private static final int ACE_UPWARD_CONDITION = 11;
	private static final int ACE_UPWARD_SCORE = 10;
	private List<Card> cards;

	public Hand(List<Card> cards) {
		Objects.requireNonNull(cards);
		this.cards = new ArrayList<>(cards);
	}

	void add(Card card) {
		cards.add(card);
	}

	public Score calculate() {
		int totalScore = computeAceBy(sumAllCard(cards), hasAce(cards));
		return new Score(totalScore, isFirstDraw());
	}

	private int computeAceBy(int totalScore, boolean hasAce) {
		if (totalScore <= ACE_UPWARD_CONDITION && hasAce) {
			totalScore += ACE_UPWARD_SCORE;
		}
		return totalScore;
	}

	private int sumAllCard(List<Card> cards) {
		return cards.stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	private boolean hasAce(List<Card> cards) {
		return cards.stream().anyMatch(Card::isAce);
	}

	private boolean isFirstDraw() {
		return cards.size() == FIRST_DRAW_COUNT;
	}

	public List<Card> getCards() {
		return cards;
	}
}