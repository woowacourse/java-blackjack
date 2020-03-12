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
		return this.type == Type.ACE;
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
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Card card = (Card)o;
		return symbol == card.symbol &&
			type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, type);
	}

}
