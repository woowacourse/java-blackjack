package blackjack.domain.result;

import java.util.Objects;

import blackjack.domain.card.Hand;
import blackjack.domain.exceptions.InvalidResultScoreException;

public class ResultScore implements Comparable<ResultScore> {
	private final Score score;
	private final ScoreType scoreType;

	public ResultScore(Score score, ScoreType scoreType) {
		validate(score, scoreType);
		this.score = score;
		this.scoreType = scoreType;
	}

	private void validate(Score score, ScoreType scoreType) {
		if (Objects.isNull(score) || Objects.isNull(scoreType)) {
			throw new InvalidResultScoreException(InvalidResultScoreException.NULL);
		}
	}

	public static ResultScore of(Hand hand) {
		Score score = hand.calculateScore();
		ScoreType scoreType = ScoreType.of(score, hand.isInitialDealtSize());

		return new ResultScore(hand.calculateScore(), scoreType);
	}

	public boolean isBlackjack() {
		return scoreType.isBlackjack();
	}

	public boolean isBust() {
		return scoreType.isBust();
	}

	public boolean isNormal() {
		return !isBlackjack() && !isBust();
	}

	@Override
	public int compareTo(ResultScore that) {
		return this.score.compareTo(that.score);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		ResultScore that = (ResultScore)object;
		return Objects.equals(score, that.score) &&
			scoreType == that.scoreType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, scoreType);
	}
}
