package blackjack.domain.card;

public enum CardShape {

	HEART("하트"),
	CLOVER("클로버"),
	SPADE("스페이드"),
	DIAMOND("다이아몬드");

	private final String name;

	CardShape(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
