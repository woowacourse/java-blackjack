package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private final Map<String, Double> userResult;
	private final double dealerIncome;

	public GameResult(List<User> users, Dealer dealer) {
		Map<String, Double> userResult = new HashMap<>();
		double dealerIncome = Money.ZERO;
		for (User user : users) {
			double userIncome = user.compareScore(dealer);
			userResult.put(user.getName(), userIncome);
			dealerIncome -= userIncome;
		}
		this.userResult = userResult;
		this.dealerIncome = dealerIncome;
	}

	public Map<String, Double> getUserResult() {
		return userResult;
	}

	public double getDealerIncome() {
		return dealerIncome;
	}
}
