package domains.result;

import java.util.HashMap;
import java.util.Map;

import domains.user.Player;
import domains.user.money.BettingMoney;
import domains.user.money.ProfitMoney;

public class Profits {
	private Map<Player, ProfitMoney> playerProfits;

	public Profits(Map<Player, ResultType> playerResult, Map<Player, BettingMoney> playersBettingMoney) {
		Map<Player, ProfitMoney> playerProfits = new HashMap<>();
		for (Player player : playerResult.keySet()) {
			ResultType resultType = playerResult.get(player);
			BettingMoney bettingMoney = playersBettingMoney.get(player);
			playerProfits.put(player, resultType.calculateProfitMoney(bettingMoney));
		}
		this.playerProfits = playerProfits;
	}

	public ProfitMoney createDealerProfit() {
		return ProfitMoney.calculateDealerProfit(playerProfits.values());
	}

	public Map<Player, ProfitMoney> getPlayerProfits() {
		return playerProfits;
	}
}
