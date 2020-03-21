package domain;

import java.util.HashMap;
import java.util.Map;

public class BettingMoneys {
	private final Map<Player, BettingMoney> bettingMoneys;

	public BettingMoneys(Map<Player, BettingMoney> bettingMoneys) {
		this.bettingMoneys = new HashMap<>(bettingMoneys);
	}

	public BettingMoney get(Player player) {
		return bettingMoneys.getOrDefault(player, BettingMoney.ZERO);
	}
}
;