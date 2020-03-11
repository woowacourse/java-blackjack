package domain;

import domain.player.Dealer;
import domain.player.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
	private static final int INITIAL_RESULT_COUNT = 0;

	private final Map<String, Result> userResult;
	private final Map<Result, Integer> dealerResult;

	public GameResult(List<User> users, Dealer dealer) {
		this.userResult = new HashMap<>();
		this.dealerResult = new HashMap<>();
		for (Result value : Result.values()) {
			dealerResult.put(value, INITIAL_RESULT_COUNT);
		}

		for (User user : users) {
			this.userResult.put(user.getName(), compareScore(user, dealer));
		}
	}

	public Result compareScore(User user, Dealer dealer) {
		int dealerScore = dealer.calculateScore();
		int userScore = user.calculateScore();
		if (dealerScore > Rull.MAX_SCORE) {
			return dealerBurst(userScore);
		}
		return dealerNotBurst(dealerScore, userScore);
	}

	private Result dealerBurst(int userScore) {
		if (userScore > Rull.MAX_SCORE) {
			return userLoseCase();
		}
		return userWinCase();
	}

	private Result dealerNotBurst(int dealerScore, int userScore) {
		if (userScore > Rull.MAX_SCORE || dealerScore > userScore) {
			return userLoseCase();
		}
		if (dealerScore == userScore) {
			return userDrawCase();
		}
		return userWinCase();
	}

	private Result userDrawCase() {
		this.dealerResult.put(Result.DRAW, dealerResult.get(Result.DRAW) + 1);
		return Result.DRAW;
	}

	private Result userWinCase() {
		dealerResult.put(Result.LOSE, dealerResult.get(Result.LOSE) + 1);
		return Result.WIN;
	}

	private Result userLoseCase() {
		dealerResult.put(Result.WIN, dealerResult.get(Result.WIN) + 1);
		return Result.LOSE;
	}

	public Map<String, Result> getUserResult() {
		return userResult;
	}

	public Map<Result, Integer> getDealerResult() {
		return dealerResult;
	}
}
