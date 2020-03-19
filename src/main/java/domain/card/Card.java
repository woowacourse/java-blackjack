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
		return symbol.isAce();
	}

	public String getCardInfo() {
		return symbol.name() + " " + type.name();
	}

	public int getScore() {
		return this.symbol.getScore();
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
