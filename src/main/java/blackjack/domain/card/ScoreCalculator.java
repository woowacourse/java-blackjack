package blackjack.domain.card;

import java.util.List;

public class ScoreCalculator {
	public static Score calculateScore(List<Card> cards) {
		Score score = cards.stream()
			.map(Score::valueOf)
			.reduce(Score.ZERO, Score::add);
		return aceHandled(score, hasAce(cards));
	}

	private static Score aceHandled(Score score, boolean hasAce) {
		if (hasAce && isNotBustBy(score)) {
			score = score.add(Score.ADDITIONAL_ACE_SCORE);
		}
		return score;
	}

	private static boolean hasAce(List<Card> cards) {
		return cards.stream()
			.anyMatch(Card::isAce);
	}

	private static boolean isNotBustBy(Score score) {
		return score.add(Score.ADDITIONAL_ACE_SCORE).getScore() < Score.BUST_SCORE;
	}

	public static Score calculateBustHandledScore(List<Card> cards) {
		Score score = ScoreCalculator.calculateScore(cards);

		if (score.isMoreThan(Score.BUST_SCORE)) {
			return Score.ZERO;
		}
		return score;
	}
}
