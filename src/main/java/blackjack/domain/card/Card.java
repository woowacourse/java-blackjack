package blackjack.domain.card;

import java.util.Objects;

public class Card {
	private final CardPattern pattern;
	private final CardNumber number;

	public Card(CardPattern pattern, CardNumber number) {
		this.pattern = pattern;
		this.number = number;
	}

	public int addPoint(int point) {
		return number.addNumber(point);
	}

	public boolean isAce() {
		return number.equals(CardNumber.ACE);
	}

	public String getPatternName() {
		return pattern.getName();
	}

	public String getNumberName() {
		return number.getName();
	}

	public int getNumber() {
		return number.getNumber();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Card card = (Card)o;
		return pattern == card.pattern && number == card.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pattern, number);
	}
}
