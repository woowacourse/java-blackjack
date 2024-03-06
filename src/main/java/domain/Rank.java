package domain;

public enum Rank {
	A("A"),
	TWO("2"),
	THREE("3"),
	FOUR("4"),
	FIVE("5"),
	SIX("6"),
	SEVEN("7"),
	EIGHT("8"),
	NINE("9"),
	TEN("10"),
	K("K"),
	Q("Q"),
	J("J");

	private final String name;

	Rank(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
