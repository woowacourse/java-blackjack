package domain;

import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {
	public static Results calculate(Dealer dealer, Players players) {
		List<Result> results = new ArrayList<>();
		results.add(calculateByDealer(dealer, players));
		results.addAll(calculateByPlayers(players, dealer));
		return new Results(results);
	}

	private static Result calculateByDealer(Dealer dealer, Players players) {
		List<Boolean> result = new ArrayList<>();
		players.forEach(player -> result.add(dealer.isWin(player)));
		int winCount = (int)result.stream().filter(Boolean::booleanValue).count();
		int loseCount = result.size() - winCount;
		return new Result(dealer.getName(), winCount, loseCount);
	}

	private static List<Result> calculateByPlayers(Players players, Dealer dealer) {
		List<Result> results = new ArrayList<>();
		players.forEach(player -> results.add(calculateByPlayer(player, dealer)));
		return results;
	}

	private static Result calculateByPlayer(Player player, Dealer dealer) {
		int winCount = 0;
		int loseCount = 0;
		if (player.isWin(dealer)) {
			winCount++;
		}
		if (dealer.isWin(player)) {
			loseCount++;
		}
		return new Result(player.getName(), winCount, loseCount);
	}
}
