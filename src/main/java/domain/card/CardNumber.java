package domain.card;

public enum CardNumber {
	ACE(1, "A"),
	TWO(2, "2"),
	THREE(3, "3"),
	FOUR(4, "4"),
	FIVE(5, "5"),
	SIX(6, "6"),
	SEVEN(7, "7"),
	EIGHT(8, "8"),
	NIGHT(9, "9"),
	TEN(10, "10"),
	JACK(10, "J"),
	QUEEN(10, "Q"),
	KING(10, "K");

	private final int score;
	private final String cardInitial;

	CardNumber(int score, String cardInitial) {
		this.score = score;
		this.cardInitial  = cardInitial;
	}

	public int getScore() {
		return score;
	}

	public String getCardInitial() {
		return cardInitial;
	}
}
