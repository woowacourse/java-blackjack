package blackjack.domain.game;

import blackjack.domain.role.Role;
import java.util.HashMap;
import java.util.Map;

public class Betting {

	private final Map<Role, Money> betting;

	public Betting(Map<Role, Money> bettingMoney) {
		this.betting = new HashMap<>(bettingMoney);
	}

	public Map<Role, Money> settle(Compete compete) {
		Map<Role, Money> revenue = new HashMap<>();
		for (Role player : betting.keySet()) {
			Outcome outcome = compete.getPlayerCompeteResults(player);
			revenue.put(player, getBettingResult(outcome, betting.get(player)));
		}
		return revenue;
	}

	private Money getBettingResult(Outcome outcome, Money bettingMoney) {
		return outcome.applyBettingMultiplier(bettingMoney);
	}
}
