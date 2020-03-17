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

	// TODO: 2020-03-17 ScoreCalculator 클래스로 분리
	public Score calculateScore() {
		Score score = cards.stream()
			.map(Score::valueOf)
			.reduce(Score.ZERO, Score::add);
		return aceHandled(score);
	}

	private Score aceHandled(Score score) {
		if (hasAce() && isNotBustBy(score)) {
			score = score.add(Score.ADDITIONAL_ACE_SCORE);
		}
		return score;
	}

	private boolean hasAce() {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	private boolean isNotBustBy(Score score) {
		return score.add(Score.ADDITIONAL_ACE_SCORE).getScore() < Score.BUST_SCORE;
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
