package blackjack.domain;

public enum CardSuit {
	HEART("하트"),
	SPADE("스페이드"),
	DIAMOND("다이아몬드"),
	CLOVER("클로버");

	private final String name;

	CardSuit(final String name) {
		this.name = name;
	}

	public String getValue() {
		return name;
	}
}
