package blakcjack.domain.card;

public enum CardSymbol {
	CLUB("클로버"),
	DIAMOND("다이아몬드"),
	HEART("하트"),
	SPADE("스페이드");

	private final String name;

	CardSymbol(String name) {
		this.name = name;
	}
}
