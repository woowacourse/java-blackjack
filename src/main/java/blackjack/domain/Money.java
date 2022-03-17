package blackjack.domain;

public class Money {
	private static final String BLANK = "";
	private static final String ERROR_MESSAGE_BLANK = "[ERROR] 배팅 금액은 공백일 수 없습니다.";
	private static final String ERROR_MESSAGE_TYPE = "[ERROR] 배팅 금액은 숫자로 입력해야 합니다";
	private static final String ERROR_MESSAGE_POSITIVE = "[ERROR] 배팅 금액은 양수로 입력해야 합니다.";
	private static final String ERROR_MESSAGE_UNIT = "[ERROR] 배팅 금액은 10원 단위로 입력해야 합니다.";
	private static final int MIN_UNIT = 10;
	private final int amount;

	public Money(int amount) {
		this.amount = amount;
	}

	public static Money from(String input) {
		int amount = ValidateAmount(input);
		return new Money(amount);
	}

	private static int ValidateAmount(String input) {
		checkBlank(input);
		int amount;
		checkType(input);
		amount = Integer.parseInt(input);
		checkPositive(amount);
		checkUnit(amount);
		return amount;
	}

	private static void checkBlank(String input) {
		if (input.equals(BLANK)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_BLANK);
		}
	}

	private static void checkType(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERROR_MESSAGE_TYPE);
		}
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
