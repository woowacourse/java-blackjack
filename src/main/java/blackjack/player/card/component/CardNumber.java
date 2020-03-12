package blackjack.player.card.component;

public enum CardNumber {
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

	private final int number;
	private final String message;

	CardNumber(int number, String message) {
		this.number = number;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getNumber() {
		return number;
	}
}