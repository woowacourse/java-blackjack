package blackjack.domain.card;

public enum CardNumber {

	ACE("A", 11),
	TWO("2", 2),
	THREE("3", 3),
	FOUR("4", 4),
	FIVE("5", 5),
	SIX("6", 6),
	SEVEN("7", 7),
	EIGHT("8", 8),
	NINE("9", 9),
	TEN("10", 10),
	JACK("J", 10),
	QUEEN("Q", 10),
	KING("K", 10);

	private final String name;
	private final int value;

	CardNumber(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
