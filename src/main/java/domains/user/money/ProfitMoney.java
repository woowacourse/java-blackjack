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

	public static ProfitMoney calculateDealerProfit(Collection<ProfitMoney> playerProfits) {
		int totalPlayerProfit = playerProfits.stream()
			.mapToInt(profit -> profit.money)
			.sum();
		return new ProfitMoney(REVERSE_SIGN * totalPlayerProfit);
	}
}
