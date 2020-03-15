package blackjack.domain;

import blackjack.domain.card.Score;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class Results {
	private final Map<Player, Boolean> playerResults;
	private final int dealerWin;
	private final int dealerLose;

	private Results(Map<Player, Boolean> playerResults, int dealerWin, int dealerLose) {
		this.playerResults = playerResults;
		this.dealerWin = dealerWin;
		this.dealerLose = dealerLose;
	}

	public static Results of(Dealer dealer, Players players) {
		Map<Player, Boolean> playerResults = createPlayersResult(dealer, players);
		int dealerWin = calculateDealerWin(playerResults);
		int dealerLose = calculateDealerLose(playerResults);

		return new Results(playerResults, dealerWin, dealerLose);
	}

	private static Map<Player, Boolean> createPlayersResult(Dealer dealer, Players players) {
		Map<Player, Boolean> playerResults = new LinkedHashMap<>();
		Score dealerScore = dealer.getScore();
		for (Player player : players.getPlayers()) {
			playerResults.put(player, player.isWinner(dealerScore));
		}

		return playerResults;
	}

	private static int calculateDealerWin(Map<Player, Boolean> playerResults) {
		return (int) playerResults.values().stream()
				.filter(isWinner -> !isWinner)
				.count();
	}

	private static int calculateDealerLose(Map<Player, Boolean> playerResults) {
		return (int) playerResults.values().stream()
				.filter(isWinner -> isWinner)
				.count();
	}

	public boolean getResult(Player player) {
		return playerResults.get(player);
	}

	public Map<Player, Boolean> getPlayerResults() {
		return playerResults;
	}

	public int getDealerWin() {
		return dealerWin;
	}

	public int getDealerLose() {
		return dealerLose;
	}
}
