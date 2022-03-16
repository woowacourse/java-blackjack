package blackjack.domain.card;

public enum Suit {
	HEART("♥️"),
	CLOVER("♣️"),
	SPADE("♠️"),
	DIAMOND("♦️"),
	;

	private final String name;

	Suit(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
