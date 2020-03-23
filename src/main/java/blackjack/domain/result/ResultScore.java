package blackjack.domain.result;

import java.util.List;
import java.util.Objects;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.card.Card;
import blackjack.domain.exceptions.InvalidResultScoreException;

public class ResultScore implements Comparable<ResultScore> {
	private final Score score;
	private final HandType handType;

	ResultScore(Score score, HandType handType) {
		validate(score, handType);
		this.score = score;
		this.handType = handType;
	}

	private void validate(Score score, HandType handType) {
		if (Objects.isNull(score) || Objects.isNull(handType)) {
			throw new InvalidResultScoreException(InvalidResultScoreException.SCORE_OR_SCORE_TYPE_NULL);
		}
	}

	public static ResultScore of(List<Card> cards) {
		validate(cards);
		Score score = calculateScore(cards);
		HandType handType = HandType.of(score, isInitialDealtSize(cards));
		return new ResultScore(score, handType);
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

	public boolean isLowerThan(int compareScore) {
		return score.isLowerThan(compareScore);
	}

	public boolean isBlackjack() {
		return handType.isBlackjack();
	}

	public boolean isBust() {
		return handType.isBust();
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
			handType == that.handType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, handType);
	}
}
