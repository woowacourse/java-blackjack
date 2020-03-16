package blackjack.domain.card;

import java.util.Objects;

public class Card {
	private final Symbol symbol;
	private final Type type;

	Card(Symbol symbol, Type type) {
		this.symbol = symbol;
		this.type = type;
	}

	public static Card of(Symbol symbol, Type type) {
		return CardFactory.pickUp(symbol, type)
			.orElse(new Card(symbol, type));
	}

	public boolean isAce() {
		return symbol.isAce();
	}

	public static String name(Symbol symbol, Type type) {
		return String.format("%s%s", symbol, type);
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
