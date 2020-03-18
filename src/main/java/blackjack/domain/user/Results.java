package blackjack.domain.user;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Results {
	private final Map<Playable, Result> playerResults;
	private final int dealerWin;
	private final int dealerDraw;
	private final int dealerLose;

	private Results(Map<Playable, Result> playerResults, int dealerWin, int dealerDraw, int dealerLose) {
		this.playerResults = playerResults;
		this.dealerWin = dealerWin;
		this.dealerDraw = dealerDraw;
		this.dealerLose = dealerLose;
	}

	public static Results of(Playable dealer, Players players) {
		Map<Playable, Result> playerResults = createPlayersResult(dealer, players);
		int dealerWin = calculateDealerWin(playerResults);
		int dealerDraw = calculateDealerDraw(playerResults);
		int dealerLose = calculateDealerLose(playerResults);

		return new Results(playerResults, dealerWin, dealerDraw, dealerLose);
	}

	private static Map<Playable, Result> createPlayersResult(Playable dealer, Players players) {
		Map<Playable, Result> playerResults = new LinkedHashMap<>();
		for (Playable player : players.getPlayers()) {
			playerResults.put(player, Result.getPlayerResultByDealer(player, dealer));
		}

		return playerResults;
	}

	private static int calculateDealerWin(Map<Playable, Result> playerResults) {
		return (int) playerResults.values().stream()
				.filter(Result::isLose)
				.count();
	}

	private static int calculateDealerDraw(Map<Playable, Result> playerResults) {
		return (int) playerResults.values().stream()
				.filter(Result::isDraw)
				.count();
	}

	private static int calculateDealerLose(Map<Playable, Result> playerResults) {
		return (int) playerResults.values().stream()
				.filter(Result::isWinOrBlackjackWin)
				.count();
	}

	public Result getResult(Playable player) {
		return playerResults.get(player);
	}

	public Map<Playable, Result> getPlayerResults() {
		return playerResults;
	}

	public int getDealerWin() {
		return dealerWin;
	}

	public int getDealerDraw() {
		return dealerDraw;
	}

	public int getDealerLose() {
		return dealerLose;
	}


}
