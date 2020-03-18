package domains.user.money;

import java.util.Collection;

public class ProfitMoney extends Money {
	private static final int REVERSE_SIGN = -1;

	public ProfitMoney(String money) {
		checkValidation(money);
		this.money = Integer.parseInt(money);
	}

	public ProfitMoney(int money) {
		this(String.valueOf(money));
	}


	public static ProfitMoney calculateDealerProfit(Collection<ProfitMoney> profits) {
		int playerProfitAmount = profits.stream()
			.mapToInt(profit -> profit.money)
			.sum();
		int dealerProfit = REVERSE_SIGN * playerProfitAmount;
		return new ProfitMoney(dealerProfit);
	}
}
