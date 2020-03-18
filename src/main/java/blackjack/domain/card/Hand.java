package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.result.Score;
import blackjack.domain.result.ScoreCalculator;

public class Hand {
	private final List<Card> cards;

	public Hand() {
		this.cards = new ArrayList<>();
	}

	public void add(Card card) {
		cards.add(card);
	}

	public void add(List<Card> cards) {
		this.cards.addAll(cards);
	}

	public Score calculateScore() {
		return ScoreCalculator.calculateScore(cards);
	}

	public Score calculateBustHandledScore() {
		return ScoreCalculator.calculateBustHandledScore(cards);
	}

	public boolean isInitialDealtSize() {
		return cards.size() == BlackjackTable.INITIAL_DEAL_NUMBER;
	}

	public List<Card> getCards() {
		return cards;
	}
}
