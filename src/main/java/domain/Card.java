package domain;

import java.util.Objects;

public class Card {
	private final Number number;
	private final Type type;

	public Card(Number number, Type type) {
		this.number = number;
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Card card = (Card) o;
		return number == card.number && type == card.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, type);
	}
}
