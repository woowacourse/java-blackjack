package blackjack.domain.gamer;

import java.util.Objects;

public class Bet {

	public static final String INVALID_RANGE_ERROR = "금액은 0보다 커야 합니다";
	public static final int INVALID_MAX_VALUE = 0;

	private final int value;

	public Bet(int value) {
		validateValue(value);
		this.value = value;
	}

	private void validateValue(int value) {
		if (value <= INVALID_MAX_VALUE) {
			throw new IllegalArgumentException(INVALID_RANGE_ERROR);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Bet money = (Bet)o;
		return value == money.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
