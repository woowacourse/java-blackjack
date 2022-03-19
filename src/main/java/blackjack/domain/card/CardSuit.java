package blackjack.domain.card;

public enum CardSuit {
	HEART("♥"),
	SPADE("♠"),
	DIAMOND("♦"),
	CLOVER("♣");

	private final String name;

	CardSuit(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
