package domain;

import java.util.Objects;

public class Card {
	private final Type type;
	private final Symbol symbol;

	private Card(Type type, Symbol symbol) {
		this.type = type;
		this.symbol = symbol;
	}

	public static Card of(String type, String symbol) {
		return of(Type.of(type), Symbol.of(symbol));
	}

	public static Card of(Type type, Symbol symbol) {
		if (type == null || symbol == null) {
			throw new IllegalArgumentException("카드의 타입 및 심볼은 NULL이 될 수 없습니다.");
		}
		return new Card(type, symbol);
	}

	public boolean isAce() {
		return symbol.isAce();
	}

	public int getPoint() {
		return symbol.getPoint();
	}

	public String getName() {
		return symbol.getAlias() + type.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card)o;
		return type == card.type &&
				symbol == card.symbol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, symbol);
	}
}
