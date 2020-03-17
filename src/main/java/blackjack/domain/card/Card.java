package blackjack.domain.card;

public class Card {
	private final Symbol symbol;
	private final Type type;

	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	public boolean isAce() {
		return this.symbol.isAce();
	}

	public int getScore() {
		return symbol.getScore();
	}

	@Override
	public String toString() {
		return symbol.getSymbol() + type.getType();
	}
}
