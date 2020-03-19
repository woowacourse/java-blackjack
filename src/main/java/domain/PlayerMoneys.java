package domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import domain.user.Player;
import domain.user.User;

public class PlayerMoneys {
	private static final int MULTIPLIER_TO_REVERSE = -1;

	private final Map<Player, Integer> playerMoneys;

	public PlayerMoneys(Map<Player, Integer> playerMoneys) {
		this.playerMoneys = playerMoneys;
	}

	public Map<User, Double> getTotalPrizes(User dealer) {
		Map<Player, Double> playerProfits = getPlayerProfitsBy(dealer);

		Map<User, Double> totalResult = getUserProfitsOf(dealer, playerProfits);

		return totalResult;
	}

	private Map<Player, Double> getPlayerProfitsBy(User dealer) {
		Map<Player, Double> playerProfits = new HashMap<>();

		playerMoneys.keySet().forEach(player ->
			playerProfits.put(player, ResultType.from(player, dealer).getProfit(playerMoneys.get(player)))
		);

		return playerProfits;
	}

	private Map<User, Double> getUserProfitsOf(User dealer, Map<Player, Double> playerProfits) {
		Map<User, Double> userProfit = new LinkedHashMap<>();

		userProfit.put(dealer, playerProfits.values().stream()
			.mapToDouble(playerProfit -> playerProfit * MULTIPLIER_TO_REVERSE)
			.sum());

		playerProfits.forEach((userProfit::put));

		return userProfit;
	}
}
