package domain.card;

import java.util.Objects;

public class Card {
	private final Symbol symbol;
	private final Type type;

	public Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	public boolean isAce() {
		return type.isAce();
	}

	public int getTypeScore() {
		return this.type.getScore();
	}

	public String getTypeName() {
		return this.type.getName();
	}

	public String getSymbol() {
		return symbol.getName();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Card that = (Card)object;
		return this.symbol == that.symbol &&
			this.type == that.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, type);
	}
}
