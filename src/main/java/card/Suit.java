package card;

public enum Suit {
	HEART("하트"),
	SPADE("스페이드"),
	CLUB("클로버"),
	DIAMOND("다이아몬드");

	private final String name;

	Suit(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
