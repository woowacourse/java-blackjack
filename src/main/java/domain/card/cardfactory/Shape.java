package domain.card.cardfactory;

public enum Shape {
	DIAMOND("다이아몬드"),
	SPADE("스페이드"),
	HART("하트"),
	CLOVER("클로버");

	private final String name;

	Shape(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
