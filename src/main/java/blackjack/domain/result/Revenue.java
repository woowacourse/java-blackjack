package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.Blackjack;
import blackjack.domain.participant.Player;

public class Revenue {
	private final Map<Player, Integer> revenue;

	public Revenue(Map<Player, Integer> revenue) {
		this.revenue = revenue;
	}

	public static Revenue of(Blackjack blackjack) {
		Map<Player, Integer> revenue = new HashMap<>();
		for (Player player : blackjack.getPlayers()) {
			Result result = Result.of(blackjack.getDealer(), player);
			int betAmount = player.getBetAmount();
			revenue.put(player, result.getEarning(betAmount));
		}
		return new Revenue(revenue);
	}

	public int getTotalRevenue() {
		return revenue.values().stream()
			.mapToInt(value -> value)
			.sum();
	}

	public int getRevenue(Player player) {
		return revenue.get(player);
	}
}
