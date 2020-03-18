package domain.user;

public class BettingMoney {
	private static final String BETTING_MONEY_RANGE_EXCEPTION_MESSAGE = "베팅 금액은 1원 이상이여야합니다.";
	private static final int MIN_BETTING_MONEY = 1;

	private double bettingMoney;

	public BettingMoney(String inputBettingMoney) {
		this(Double.parseDouble(inputBettingMoney));
	}

	public BettingMoney(double bettingMoney) {
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private void validate(double bettingMoney) {
		if (bettingMoney < MIN_BETTING_MONEY) {
			throw new IllegalArgumentException(BETTING_MONEY_RANGE_EXCEPTION_MESSAGE);
		}
	}

	public double getBettingMoney() {
		return bettingMoney;
	}
}
