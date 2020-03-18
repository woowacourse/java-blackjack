package domain.user;

public class BettingMoney {
	private static final int MINIMUM_BETTING_MONEY = 1;
	private static final String NOT_ENOUGH_BETTING_MONEY_EXCEPTION_MESSAGE = "해당 금액으로는 배팅하실 수 없습니다.";
	private final int money;

	public BettingMoney(int money) {
		validate(money);
		this.money = money;
	}

	private void validate(int money) {
		if (money < MINIMUM_BETTING_MONEY) {
			throw new IllegalArgumentException(NOT_ENOUGH_BETTING_MONEY_EXCEPTION_MESSAGE);
		}
	}
}
