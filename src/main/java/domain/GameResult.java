package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private final Map<String, Double> userIncomes;
	private final double dealerIncome;

	public GameResult(List<User> users, Dealer dealer) {
		Map<String, Double> userIncomes = new HashMap<>();
		double dealerIncome = Money.ZERO;
		for (User user : users) {
			double userIncome = user.compareScore(dealer);
			userIncomes.put(user.getName(), userIncome);
			dealerIncome -= userIncome;
		}
		this.userIncomes = userIncomes;
		this.dealerIncome = dealerIncome;
	}

	public Map<String, Double> getUserIncomes() {
		return userIncomes;
	}

	public double getDealerIncome() {
		return dealerIncome;
	}
}
