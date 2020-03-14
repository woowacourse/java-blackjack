package domain.card;

public enum Symbol {
	ACE(1, "A"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NINE(9, "9"),
	TEN(10, "10"),
	JACK(10, "J"),
	QUEEN(10, "Q"),
	KING(10, "K");

	private static final int ACE_NEED_TO_BE_BIG_SCORE = 11;
	private static final int ACE_GAP = 10;

	private final int score;
	private final String name;

	Symbol(int score, String name) {
		this.score = score;
		this.name = name;
	}

	public int getBigOrSmallAce(int userScore) {
		if (userScore <= ACE_NEED_TO_BE_BIG_SCORE) {
			return ACE.score + ACE_GAP;
		}
		return ACE.score;
	}

	public int getScore() {
		return score;
	}

	public String getName() {
		return name;
	}
}
