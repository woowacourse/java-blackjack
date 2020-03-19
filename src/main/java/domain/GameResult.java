package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private final Map<String, Double> userResult;

	public GameResult(List<User> users, Dealer dealer, boolean firstDrawBlackJack) {
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
				.orElse((double) Money.ZERO_MONEY) * Money.MINUS_CONVERTER;
	}
}
