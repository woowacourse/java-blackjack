package blackjack.domain.result;

import java.util.List;
import java.util.Objects;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.card.Card;
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
			throw new InvalidResultScoreException(InvalidResultScoreException.SCORE_OR_SCORE_TYPE_NULL);
		}
	}

	public static ResultScore of(List<Card> cards) {
		validate(cards);
		Score score = calculateScore(cards);
		ScoreType scoreType = ScoreType.of(score, isInitialDealtSize(cards));
		return new ResultScore(score, scoreType);
	}

	private static void validate(List<Card> cards) {
		if (Objects.isNull(cards) || cards.isEmpty()) {
			throw new InvalidResultScoreException(InvalidResultScoreException.CARDS_EMPTY);
		}
	}

	private static Score calculateScore(List<Card> cards) {
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

	private static boolean isInitialDealtSize(List<Card> cards) {
		return BlackjackTable.INITIAL_DEAL_NUMBER == cards.size();
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

	public Score getScore() {
		return score;
	}

	public int getIntScore() {
		return score.getScore();
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
