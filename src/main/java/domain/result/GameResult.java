package domain.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

public class GameResult {
	private final Map<Player, Profit> playerToProfit;
	private final Map<Gamer, Score> scores;

	private GameResult(Map<Player, Profit> playerToProfit, Map<Gamer, Score> scores) {
		this.playerToProfit = playerToProfit;
		this.scores = scores;
	}

	public static GameResult of(List<Player> players, Dealer dealer) {
		Map<Player, Profit> playerToProfit = new HashMap<>();
		Map<Gamer, Score> scores = new LinkedHashMap<>();

		scores.put(dealer, dealer.getScore());
		for (Player player : players) {
			ResultType result = ResultType.of(player, dealer);
			playerToProfit.put(player, new Profit(result.calculateProfit(player.getMoney())));
			scores.put(player, player.getScore());
		}
		return new GameResult(playerToProfit, scores);
	}

	public Profit dealerResult() {
		Profit profit = Profit.ZERO;
		for (Profit each : playerToProfit.values()) {
			profit = profit.plus(each.negative());
		}
		return profit;
	}

	public Map<Player, Profit> getPlayerToProfit() {
		return Collections.unmodifiableMap(playerToProfit);
	}

	public Map<Gamer, Score> getScores() {
		return Collections.unmodifiableMap(scores);
	}
}
