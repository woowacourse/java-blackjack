package domain.card;

public enum Rank {
	ACE(Score.from(11)),
	TWO(Score.from(2)),
	THREE(Score.from(3)),
	FOUR(Score.from(4)),
	FIVE(Score.from(5)),
	SIX(Score.from(6)),
	SEVEN(Score.from(7)),
	EIGHT(Score.from(8)),
	NINE(Score.from(9)),
	TEN(Score.from(10)),
	JACK(Score.from(10)),
	QUEEN(Score.from(10)),
	KING(Score.from(10));

	private static final Score ACE_MIN = Score.from(1);

	private final Score score;

	Rank(final Score score) {
		this.score = score;
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
