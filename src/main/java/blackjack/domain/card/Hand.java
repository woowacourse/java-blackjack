package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

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
		Score score = calculateScore();

		if (score.isMoreThan(Score.BUST_SCORE)) {
			return Score.ZERO;
		}
		return score;
	}

	public List<Card> getCards() {
		return cards;
	}
}
