package domain;

public class Card {
	private Type type;
	private Symbol symbol;

	public Card(Type type, Symbol symbol) {
		this.type = type;
		this.symbol = symbol;
	}

	public int score() {
		return symbol.getScore();
	}

	public boolean isAce() {
		return this.symbol == Symbol.ACE;
	}
}
