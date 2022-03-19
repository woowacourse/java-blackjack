package blackjack.domain;

public class Money {
	private static final String ERROR_MESSAGE_POSITIVE = "[ERROR] 배팅 금액은 양수로 입력해야 합니다.";
	private static final String ERROR_MESSAGE_UNIT = "[ERROR] 배팅 금액은 10원 단위로 입력해야 합니다.";
	private static final int MIN_UNIT = 10;
	private final int amount;

	public Money(int amount) {
		this.amount = validateAmount(amount);
	}

	private static int validateAmount(int amount) {
		checkPositive(amount);
		checkUnit(amount);
		return amount;
	}

	private static void checkPositive(int amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException(ERROR_MESSAGE_POSITIVE);
		}
	}

	private static void checkUnit(int amount) {
		if (amount % MIN_UNIT != 0) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNIT);
		}
	}

	public int getAmount() {
		return amount;
	}
}
