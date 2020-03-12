package domain.result;

import java.util.List;
import java.util.Objects;

import domain.card.Card;
import domain.card.Symbol;

public class Score {
	private static final int ZERO = 0;
	private static final int TEN = 10;
	private static final int BLACKJACK_SCORE = 21;

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score of(int score) {
		return new Score(score);
	}

	public static Score calculate(List<Card> cards) {
		int rawScore = calculateRaw(cards);
		if (containAce(cards) && rawScore + TEN <= BLACKJACK_SCORE) {
			return new Score(rawScore + TEN);
		}
		return new Score(rawScore);
	}

	private static int calculateRaw(List<Card> cards) {
		return cards.stream()
			.map(Card::getSymbol)
			.map(Symbol::getScore)
			.reduce(ZERO, Integer::sum);
	}

	private static boolean containAce(List<Card> cards) {
		return cards.stream()
			.map(Card::getSymbol)
			.anyMatch(symbol -> symbol == Symbol.ACE);
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
		Score score1 = (Score)o;
		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
