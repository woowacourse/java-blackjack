package blackjack.domain;

public enum CardLetter {
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

	private final int letterScore;
	private final String cardName;

	CardLetter(final int letterScore, final String cardName) {
		this.letterScore = letterScore;
		this.cardName = cardName;
	}

	public int getLetterScore() {
		return letterScore;
	}

	public String getCardName() {
		return cardName;
	}
}
