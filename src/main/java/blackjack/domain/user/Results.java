package blackjack.domain.user;

import blackjack.domain.betting.Money;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Results {
	private final Map<Name, Result> results;

	private Results(Map<Name, Result> results) {
		this.results = results;
	}

	public static Results of(Playable dealer, Players players) {
		Map<Name, Result> playerResults = createPlayersResult(dealer, players);

		return new Results(playerResults);
	}

	private static Map<Name, Result> createPlayersResult(Playable dealer, Players players) {
		Map<Name, Result> results = new LinkedHashMap<>();
		for (Playable player : players.getPlayers()) {
			results.put(player.getName(), Result.getPlayerResultByDealer(player, dealer));
		}

		return results;
	}

	public Result getResult(Name name) {
		return results.get(name);
	}

	public double getResultMoney(Name name, Money money) {
		return results.get(name).computeResultAmount(money.getAmount());
	}

	public Map<Name, Result> getResults() {
		return Collections.unmodifiableMap(results);
	}

	public int getDealerWin() {
		return (int) results.values().stream()
				.filter(Result::isLose)
				.count();
	}

	public int getDealerDraw() {
		return (int) results.values().stream()
				.filter(Result::isDraw)
				.count();
	}

	public int getDealerLose() {
		return (int) results.values().stream()
				.filter(Result::isWinOrBlackjackWin)
				.count();
	}
}
