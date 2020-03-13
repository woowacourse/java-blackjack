package blackjack.domain.card;

import blackjack.domain.card.exceptions.ScoreException;

import java.util.Objects;

public class Score {
	private final int score;

	private Score(int score) {
		validateRange(score);
		this.score = score;
	}

	public static Score of(int score) {
		return new Score(score);
	}

	private void validateRange(int score) {
		if (score < 0) {
			throw new ScoreException("점수는 양수이어야 합니다.");
		}
	}

	public Score add(Score other) {
		return new Score(this.score + other.score);
	}

	public boolean isUnder(int number) {
		return score < number;
	}

	public boolean isOver(int number) {
		return score > number;
	}

	public boolean isOver(Score other) {
		return isOver(other.score);
	}

	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Score score1 = (Score) o;
		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}

	@Override
	public String toString() {
		return "Score{" +
				"score=" + score +
				'}';
	}
}
