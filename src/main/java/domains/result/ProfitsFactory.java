package domains.result;

import java.util.HashMap;
import java.util.Map;

import domains.user.Player;
import domains.user.money.BettingMoney;
import domains.user.money.ProfitMoney;

class ProfitsFactory {
	static Map<Player, ProfitMoney> create(Map<Player, ResultType> playerResult,
		Map<Player, BettingMoney> playersBettingMoney) {
		Map<Player, ProfitMoney> playerProfits = new HashMap<>();

		for (Player player : playerResult.keySet()) {
			ResultType resultType = playerResult.get(player);
			BettingMoney bettingMoney = playersBettingMoney.get(player);
			playerProfits.put(player, resultType.calculateProfitMoney(bettingMoney));
		}

		return playerProfits;
	}
}
