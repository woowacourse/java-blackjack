package blackjack.domain.card;

public enum CardSymbol {
	SPADE("♠️"),
	DIAMOND("♦️"),
	HEART("♥️"),
	CLOVER("♣️");

	private final String name;

	CardSymbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
