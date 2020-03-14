package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private final Map<String, Result> userResult;

	public GameResult(List<User> users, Dealer dealer) {
		Map<String, Result> userResult = new HashMap<>();
		for (User user : users) {
			userResult.put(user.getName(), user.compareScore(dealer));
		}
		this.userResult = userResult;
	}

	public int calculateDealerWinCount() {
		return (int) userResult.values().stream()
				.filter(Result::isLose)
				.count();
	}

	public int calculateDealerDrawCount() {
		return (int) userResult.values().stream()
				.filter(Result::isDraw)
				.count();
	}

	public int calculateDealerLoseCount() {
		return (int) userResult.values().stream()
				.filter(Result::isWin)
				.count();
	}

	public Map<String, Result> getUserResult() {
		return userResult;
	}
}
