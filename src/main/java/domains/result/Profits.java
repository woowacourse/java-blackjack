package domains.result;

import java.util.Map;

import domains.user.Player;
import domains.user.money.BettingMoney;
import domains.user.money.ProfitMoney;

public class Profits {
	private Map<Player, ProfitMoney> playerProfits;

	public Profits(Map<Player, ResultType> playerResult, Map<Player, BettingMoney> playersBettingMoney) {
		this.playerProfits = ProfitsFactory.create(playerResult, playersBettingMoney);
	}

	public ProfitMoney createDealerProfit() {
		return ProfitMoney.calculateDealerProfit(playerProfits.values());
	}

	public Map<Player, ProfitMoney> getPlayerProfits() {
		return playerProfits;
	}
}
