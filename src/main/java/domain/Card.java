package domain;

public class Card {
	private Type type;
	private int symbol;

	private Card(Type type, int symbol) {
		this.type = type;
		this.symbol = symbol;
	}

	public static Card of(String type, int symbol) {
		return new Card(Type.of(type), symbol);
	}
}
