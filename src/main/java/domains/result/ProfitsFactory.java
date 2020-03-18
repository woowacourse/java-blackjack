package domains.result;

import java.util.HashMap;
import java.util.Map;

import domains.user.Player;
import domains.user.money.ProfitMoney;

class ProfitsFactory {
	static Map<Player, ProfitMoney> create(GameResult gameResult) {
		Map<Player, ResultType> playerResult = gameResult.getPlayerResult();
		Map<Player, ProfitMoney> playerProfits = new HashMap<>();

		for (Player player : playerResult.keySet()) {
			ResultType resultType = playerResult.get(player);
			playerProfits.put(player, resultType.calculateProfitMoney(player));
		}

		return playerProfits;
	}
}