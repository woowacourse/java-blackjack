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
		validate(symbol, type);
		return CardFactory.pickUp(symbol, type);
	}

	// TODO: 2020-03-17 exception custom 진행하기, test 작성
	private static void validate(Symbol symbol, Type type) {
		if (Objects.isNull(symbol) || Objects.isNull(type)) {
			throw new IllegalArgumentException("symbol 또는 type이 존재하지 않습니다.");
		}
	}

	public boolean isAce() {
		return symbol.isAce();
	}

	static String name(Symbol symbol, Type type) {
		validate(symbol, type);
		return symbol.toString() + type.toString();
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
