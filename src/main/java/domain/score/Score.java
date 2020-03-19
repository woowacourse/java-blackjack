package domain.score;

import static java.util.Comparator.*;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.Symbol;

public class Score {
	private static final int MINIMUM_SCORE_VALUE = 0;
	private static final int MAXIMUM_SCORE_VALUE = 21;
	private static final String OUT_OF_SCORE_VALUE_BOUNDARY_EXCEPTION_MESSAGE = String.format("점수는 %d보다 작을 수 없습니다.%s",
		MINIMUM_SCORE_VALUE, System.lineSeparator());

	private final int score;

	private Score(int score) {
		validate(score);
		this.score = score;
	}

	private void validate(int score) {
		if (score < MINIMUM_SCORE_VALUE) {
			throw new IllegalArgumentException(OUT_OF_SCORE_VALUE_BOUNDARY_EXCEPTION_MESSAGE);
		}
	}

	public static Score ofValue(int score) {
		return new Score(score);
	}

	public static Score calculateTotal(List<Card> cards) {
		return reviseScore(cards, sumTotalDefaultScore(cards));
	}

	private static int sumTotalDefaultScore(List<Card> cards) {
		return cards.stream()
			.mapToInt(Card::getScore)
			.sum();
	}

	private static Score reviseScore(List<Card> cards, int defaultScore) {
		int revisedScore = cards.stream()
			.map(Card::getSymbol)
			.filter(Symbol::hasBonusScore)
			.sorted(comparingInt(Symbol::getBonusScore).reversed())
			.reduce(defaultScore, Score::appendBonusScore, Integer::sum);
		return Score.ofValue(revisedScore);
	}

	private static int appendBonusScore(int score, Symbol symbol) {
		return score + symbol.calculateBonusScore(score, MAXIMUM_SCORE_VALUE);
	}

	public boolean isBiggerThan(Score other) {
		return this.score > other.score;
	}

	public boolean isSameWith(Score other) {
		return this.score == other.score;
	}

	public boolean isSmallerThan(Score other) {
		return this.score < other.score;
	}

	public boolean isMaximum() {
		return score == MAXIMUM_SCORE_VALUE;
	}

	public boolean isBust() {
		return score > MAXIMUM_SCORE_VALUE;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Score that = (Score)object;
		return this.score == that.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}

	@Override
	public String toString() {
		return String.valueOf(score);
	}
}
