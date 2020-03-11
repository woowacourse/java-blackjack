package domain;

import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {
	public static Results getResults(Dealer dealer, Players players) {
		List<Result> results = new ArrayList<>();
		addDealerInfo(dealer, players, results);
		addPlayerInfo(dealer, players, results);
		return new Results(results);
	}

	private static void addDealerInfo(Dealer dealer, Players players, List<Result> results) {
		Result result = new Result(dealer.getName(), 0, 0);
		for (Player player : players) {
			calculateWinLose(dealer, player, result);
		}
		results.add(result);
	}

	private static void addPlayerInfo(Dealer dealer, Players players, List<Result> results) {
		for (Player player : players) {
			Result result = new Result(player.getName(), 0, 0);
			calculateWinLose(player, dealer, result);
			results.add(result);
		}
	}

	private static void calculateWinLose(User mainUser, User opponentUser, Result result) {
		if (mainUser.isWin(opponentUser)) {
			result.addWinCount();
			return;
		}
		result.addLoseCount();
	}
}
