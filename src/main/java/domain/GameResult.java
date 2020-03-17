package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private final Map<String, Double> userResult;

	public GameResult(List<User> users, Dealer dealer) {
		Map<String, Double> userResult = new HashMap<>();
		for (User user : users) {
			userResult.put(user.getName(), user.compareScore(dealer));
		}
		this.userResult = userResult;
	}

	public Map<String, Double> getUserResult() {
		return userResult;
	}

	public double calculateDealerMoney() {
		return userResult.values().stream()
				.reduce(Double::sum)
				.orElse((double) 0) * User.MINUS_CONVERTER;
	}
}
