package domain;

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

	private static final int ACE_MIN = 1;

	private final int score;

	Rank(final int score) {
		this.score = score;
	}

	public static int ifOverThanBustScoreAceIsMIN(int score, int aceCount, final int bustScore) {
		while (score > bustScore && aceCount-- > 0) {
			score -= ACE.score;
			score += ACE_MIN;
		}
		return score;
	}

	public int sum(final int score) {
		return this.score + score;
	}
}
