package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BettingMoneys {
	private Map<Player, BettingMoney> bettingMoneys;

	public BettingMoneys() {
		this(Collections.EMPTY_MAP);
	}

	public BettingMoneys(Map<Player, BettingMoney> bettingMoneys) {
		this.bettingMoneys = new HashMap<>(bettingMoneys);
	}

	public void betting(Player player, BettingMoney bettingMoney) {
		bettingMoneys.put(player, bettingMoney);
	}
}
