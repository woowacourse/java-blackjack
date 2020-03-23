package domains.user.money;

public class BettingMoney extends Money {
	private static final int MINIMUM_MONEY = 1;

	public BettingMoney(String money) {
		checkValidation(money);
		int bettingMoney = Integer.parseInt(money);
		checkNumberBound(bettingMoney);
		this.money = bettingMoney;
	}

	private void checkNumberBound(int money) {
		if (money < MINIMUM_MONEY) {
			throw new InvalidBettingMoneyException(InvalidBettingMoneyException.ZERO_OR_NEGATIVE);
		}
	}

	public ProfitMoney multiply(double profitRate) {
		return new ProfitMoney((int)(this.money * profitRate));
	}
}
