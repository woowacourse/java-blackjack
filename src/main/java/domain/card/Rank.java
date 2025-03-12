package domain.card;

public enum Rank {
	ACE(new Score(11)),
	TWO(new Score(2)),
	THREE(new Score(3)),
	FOUR(new Score(4)),
	FIVE(new Score(5)),
	SIX(new Score(6)),
	SEVEN(new Score(7)),
	EIGHT(new Score(8)),
	NINE(new Score(9)),
	TEN(new Score(10)),
	JACK(new Score(10)),
	QUEEN(new Score(10)),
	KING(new Score(10));

	private static final Score ACE_MIN = new Score(1);

	private final Score score;

	Rank(final Score score) {
		this.score = score;
	}

	public static Score ifOverThanBustScoreAceIsMIN(Score score, int aceCount, final Score bustScore) {
		while (score.isGreaterThan(bustScore) && aceCount-- > 0) {
			score = score
				.minus(ACE.score)
				.plus(ACE_MIN);
		}
		return score;
	}

	public Score sum(final Score score) {
		return score.plus(this.score);
	}
}
