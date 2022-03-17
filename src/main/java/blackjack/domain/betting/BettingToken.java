package blackjack.domain.betting;

import java.util.Objects;

public class BettingToken {
	private static final String NOT_DIVIDED_BY_UNIT_PRICE = "[ERROR] 베팅 금액은 100원 단위여야 합니다.";
	private static final String NOT_POSITIVE = "[ERROR] 베팅 금액은 음수나 0일 수 없습니다.";
	private static final int UNIT_PRICE = 100;
	private static final double PROFIT_RATIO = 1.5;
	private static final int DECREASE_RATIO = -1;

	private final int money;
	private int profit = 0;

	public BettingToken(int money) {
		validatePositive(money);
		validateDividedByUnitPrice(money);
		this.money = money;
	}

	public int getProfit() {
		return profit;
	}

	public void getBlackJackWinningMoney() {
		profit = (int) (this.money * PROFIT_RATIO);
	}

	public void getNotBlackJackWinningMoney() {
		profit = this.money;
	}

	public void getLoseMoney() {
		profit = this.money * DECREASE_RATIO;
	}

	private void validateDividedByUnitPrice(int money) {
		if (money % UNIT_PRICE != 0) {
			throw new IllegalArgumentException(NOT_DIVIDED_BY_UNIT_PRICE);
		}
	}

	private void validatePositive(int money) {
		if (money <= 0) {
			throw new IllegalArgumentException(NOT_POSITIVE);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BettingToken that = (BettingToken) o;
		return money == that.money && profit == that.profit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money, profit);
	}
}
