package domain;

public class Money {
	public static final int ZERO_MONEY = 0;
	public static final int MINUS_CONVERTER = -1;
	private static final String ERROR_MESSAGE_UNDER_ZERO = "0보다 작은 수를 입력하였습니다.";
	private final double money;

	public Money(double money) {
		validate(money);
		this.money = money;
	}

	private void validate(double money) {
		if (money <= ZERO_MONEY) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNDER_ZERO);
		}
	}

	public double getMoney() {
		return money;
	}

	public double toLoseMoney() {
		return money * MINUS_CONVERTER;
	}

	public double toBlackJackWinMoney() {
		return money * Rule.BLACK_JACK_BONUS;
	}
}
