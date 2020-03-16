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
		Score score = cards.stream()
			.map(Score::valueOf)
			.reduce(Score.ZERO, Score::add);
		return aceHandledScore(score);
	}

	private Score aceHandledScore(Score score) {
		if (hasAce() && isNotBustBy(score)) {
			score = score.add(Score.ADDITIONAL_ACE_SCORE);
		}
		return score;
	}

	private boolean isNotBustBy(Score score) {
		return score.add(Score.ADDITIONAL_ACE_SCORE).getScore() < Score.BUST_SCORE;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
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
