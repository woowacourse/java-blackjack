package blackjack.domain.user;

import blackjack.domain.card.Score;

import java.util.LinkedHashMap;
import java.util.Map;

public class Results {
	private final Map<Playable, Boolean> playerResults;
	private final int dealerWin;
	private final int dealerLose;

	private Results(Map<Playable, Boolean> playerResults, int dealerWin, int dealerLose) {
		this.playerResults = playerResults;
		this.dealerWin = dealerWin;
		this.dealerLose = dealerLose;
	}

	public static Results of(Playable dealer, Players players) {
		Map<Playable, Boolean> playerResults = createPlayersResult(dealer, players);
		int dealerWin = calculateDealerWin(playerResults);
		int dealerLose = calculateDealerLose(playerResults);

		return new Results(playerResults, dealerWin, dealerLose);
	}

	private static Map<Playable, Boolean> createPlayersResult(Playable dealer, Players players) {
		Map<Playable, Boolean> playerResults = new LinkedHashMap<>();
		Score dealerScore = dealer.getScore();
		for (Playable player : players.getPlayers()) {
			playerResults.put(player, player.isWinner(dealerScore));
		}

		return playerResults;
	}

	private static int calculateDealerWin(Map<Playable, Boolean> playerResults) {
		return (int) playerResults.values().stream()
				.filter(isWinner -> !isWinner)
				.count();
	}

	private static int calculateDealerLose(Map<Playable, Boolean> playerResults) {
		return (int) playerResults.values().stream()
				.filter(isWinner -> isWinner)
				.count();
	}

	public boolean getResult(Playable player) {
		return playerResults.get(player);
	}

	public Map<Playable, Boolean> getPlayerResults() {
		return playerResults;
	}

	public int getDealerWin() {
		return dealerWin;
	}

	public int getDealerLose() {
		return dealerLose;
	}
}
