package domain.card;

public enum Symbol {
	DIAMOND("다이아몬드"),
	CLOVER("클로버"),
	HEART("하트"),
	SPADE("스페이드");

	private final String name;

	Symbol(String name) {
		this.name = name;
	}
}
