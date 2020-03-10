package util;

import domain.*;

import java.util.*;

public class ResultCalculator {
	public static Results getResults(Dealer dealer, Players players) {
		List<Result> results = new ArrayList<>();
		int dealerWin = 0;
		int dealerLose = 0;
		for (Player player : players) {
			if (dealer.isWin(player)) {
				dealerWin++;
				continue;
			}
			dealerLose++;
		}
		results.add(new Result(dealer, dealerWin, dealerLose));
		for (Player player : players) {
			if (player.isWin(dealer)) {
				results.add(new Result(player, 1, 0));
				continue;
			}
			results.add(new Result(player, 0, 1));

		}
		return new Results(results);
	}
}
