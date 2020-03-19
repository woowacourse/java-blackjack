package blackjack.domain.user;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Results {
	private final Map<Playable, Result> playerResults;

	private Results(Map<Playable, Result> playerResults) {
		this.playerResults = playerResults;
	}

	public static Results of(Playable dealer, Players players) {
		Map<Playable, Result> playerResults = createPlayersResult(dealer, players);

		return new Results(playerResults);
	}

	private static Map<Playable, Result> createPlayersResult(Playable dealer, Players players) {
		Map<Playable, Result> playerResults = new LinkedHashMap<>();
		for (Playable player : players.getPlayers()) {
			playerResults.put(player, Result.getPlayerResultByDealer(player, dealer));
		}

		return playerResults;
	}

	public Result getResult(Playable player) {
		return playerResults.get(player);
	}

	public Map<Playable, Result> getPlayerResults() {
		return playerResults;
	}

	public int getDealerWin() {
		return (int) playerResults.values().stream()
				.filter(Result::isLose)
				.count();
	}

	public int getDealerDraw() {
		return (int) playerResults.values().stream()
				.filter(Result::isDraw)
				.count();
	}

	public int getDealerLose() {
		return (int) playerResults.values().stream()
				.filter(Result::isWinOrBlackjackWin)
				.count();
	}
}
