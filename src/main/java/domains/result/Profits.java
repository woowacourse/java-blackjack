package domains.result;

import java.util.Map;

import domains.user.Player;
import domains.user.money.ProfitMoney;

public class Profits {
	private Map<Player, ProfitMoney> playerProfits;

	public Profits(GameResult gameResult) {
		this.playerProfits = ProfitsFactory.create(gameResult);
	}

	public ProfitMoney createDealerProfit() {
		return ProfitMoney.calculateDealerProfit(playerProfits.values());
	}

	public Map<Player, ProfitMoney> getPlayerProfits() {
		return playerProfits;
	}
}
