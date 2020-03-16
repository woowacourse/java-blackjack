package domain.result;

import domain.card.Hands;
import domain.gamer.Gamer;

import java.util.Objects;

public class Score {
	private static final int TEN = 10;
	private static final int BLACKJACK_SCORE = 21;

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score from(int score) {
		return new Score(score);
	}

	public static Score from(Hands hands) {
		int score = hands.sumOfCards();

		return new Score(reviseAceScore(hands.hasAce(), score));
	}

	private static int reviseAceScore(boolean hasAce, int score) {
		if (hasAce && (score + TEN <= BLACKJACK_SCORE)) {
			score += TEN;
		}
		return score;
	}

	public boolean isBiggerThan(Score other) {
		return this.score > other.score;
	}

	public boolean isEqualTo(Score other) {
		return this.score == other.score;
	}

	public boolean isLowerThan(Score other) {
		return this.score < other.score;
	}

	public boolean isBust() {
		return this.score > BLACKJACK_SCORE;
	}

	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score1 = (Score) o;
		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
