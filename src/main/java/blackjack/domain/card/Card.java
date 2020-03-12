package blackjack.domain.card;

public class Card {
	private final Symbol symbol;
	private final Type type;

	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	public boolean isAce() {
		return this.symbol.equals(Symbol.ACE);
	}

	public int getSymbolValue() {
		return symbol.getSymbol();
	}
}
