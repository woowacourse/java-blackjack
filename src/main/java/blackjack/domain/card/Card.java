package blackjack.domain.card;

import java.util.Objects;

import blackjack.domain.exceptions.InvalidCardException;

public class Card {
	private final Symbol symbol;
	private final Type type;

	Card(Symbol symbol, Type type) {
		validate(symbol, type);
		this.symbol = symbol;
		this.type = type;
	}

	public static Card of(Symbol symbol, Type type) {
		validate(symbol, type);
		return CardFactory.pickUp(symbol, type);
	}

	static String name(Symbol symbol, Type type) {
		validate(symbol, type);
		return symbol.toString() + type.toString();
	}

	private static void validate(Symbol symbol, Type type) {
		if (Objects.isNull(symbol) || Objects.isNull(type)) {
			throw new InvalidCardException(InvalidCardException.NULL);
		}
	}

	public boolean isAce() {
		return symbol.isAce();
	}

	public int getScore() {
		return symbol.getScore();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Card card = (Card)object;
		return symbol == card.symbol &&
			type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbol, type);
	}

	@Override
	public String toString() {
		return name(this.symbol, this.type);
	}
}
