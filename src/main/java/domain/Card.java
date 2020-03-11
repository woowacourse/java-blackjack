package domain;

public class Card {
	private Symbol symbol;
	private Type type;

	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	public int score() {
		return symbol.getScore();
	}

	public boolean isAce() {
		return this.symbol == Symbol.ACE;
	}
}
