package card;

import value.Count;
import value.Score;

public enum Rank {
	ACE(11),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);

	private static final Score ACE_MIN = Score.from(1);

	private final Score score;

	Rank(final int score) {
		this.score = Score.from(score);
	}

	public static Score ifOverThanBustScoreAceIsMIN(Score score, Count aceCount, final Score bustScore) {
		while (score.isGreaterThan(bustScore) && !aceCount.isZero()) {
			score = score
				.minus(ACE.score)
				.plus(ACE_MIN);
			aceCount = aceCount.decrement();
		}
		return score;
	}

	public Score sum(final Score score) {
		return score.plus(this.score);
	}
}
