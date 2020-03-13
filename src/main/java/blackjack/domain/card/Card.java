package blackjack.domain.card;

import java.util.Objects;

public class Card {
	private final Symbol symbol;
	private final Type type;

	private Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	public static Card of(Symbol symbol, Type type) {
		return new Card(symbol, type);
	}

	public boolean isAce() {
		return symbol.isAce();
	}

	public Score getScore() {
		return symbol.getScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Card card = (Card) o;
		return symbol == card.symbol &&
				type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, type);
	}

	@Override
	public String toString() {
		return "Card{" +
				"symbol=" + symbol +
				", type=" + type +
				'}';
	}

	public String getName() {
		return symbol.getName() + " " + type.getName();
	}
}
