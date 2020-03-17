package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private static final int ZERO_MONEY = 0;
	private static final int MINUS_CONVERTER = -1;

	private final Map<String, Double> userResult;

	public GameResult(List<User> users, Dealer dealer, Boolean firstDrawBlackJack) {
		Map<String, Double> userResult = new HashMap<>();
		for (User user : users) {
			double income = user.compareScore(dealer, firstDrawBlackJack);
			userResult.put(user.getName(), income);
		}
		this.userResult = userResult;
	}

	public Map<String, Double> getUserResult() {
		return userResult;
	}

	public double calculateDealerMoney() {
		return userResult.values().stream()
				.reduce(Double::sum)
				.orElse((double) ZERO_MONEY) * MINUS_CONVERTER;
	}
}
