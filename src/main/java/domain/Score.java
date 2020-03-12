package domain;

import domain.card.Cards;

import java.util.Objects;

public class Score {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BURST_SCORE = 0;
	private static final int ACE_GAP = 10;

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score of(int number) {
		return new Score(number);
	}

	public static Score of(Cards cards) {
		int score = cards.getScore();
		if (score > BLACKJACK_SCORE) {
			return Score.of(BURST_SCORE);
		}
		if (cards.hasAce() && score <= BLACKJACK_SCORE - ACE_GAP) {
			score += ACE_GAP;
		}
		return Score.of(score);
	}

	public boolean isBlackjackScore() {
		return score == BLACKJACK_SCORE;
	}

	public boolean isNotBurst() {
		return score <= BLACKJACK_SCORE && score != BURST_SCORE;
	}

	public boolean isBiggerThan(Score other) {
		return this.minus(other) > 0;
	}

	public int minus(Score other) {
		return this.score - other.score;
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
		return Integer.toString(score);
	}
}
