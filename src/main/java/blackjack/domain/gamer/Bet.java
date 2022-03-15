package blackjack.domain.gamer;

import java.util.Objects;

public class Bet {

	public static final String INVALID_RANGE_ERROR = "금액은 0보다 커야 합니다";
	public static final int INVALID_MAX_VALUE = 0;
	public static final String INAVLID_DEVIDIBLE_VALUE_ERROR = "금액은 10원 단위여야 합니다.";

	private final int amount;

	public Bet(int amount) {
		validateValue(amount);
		this.amount = amount;
	}

	private void validateValue(int amount) {
		if (amount <= INVALID_MAX_VALUE) {
			throw new IllegalArgumentException(INVALID_RANGE_ERROR);
		}

		if (amount % 10 != 0) {
			throw new IllegalArgumentException(INAVLID_DEVIDIBLE_VALUE_ERROR);
		}
	}

	public int getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Bet money = (Bet)o;
		return amount == money.amount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}
}
