package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Results {
	private final List<Result> results;

	private Results(List<Result> results) {
		this.results = results;
	}

	public static Results of(Playable dealer, Players players) {
		List<Result> playerResults = createPlayersResult(dealer, players);

		return new Results(playerResults);
	}

	private static List<Result> createPlayersResult(Playable dealer, Players players) {
		List<Result> results = new ArrayList<>();
		for (Playable player : players.getPlayers()) {
			results.add(new Result(player, ResultType.getPlayerResultByDealer(player, dealer)));
		}

		return results;
	}

	public List<Result> getResults() {
		return Collections.unmodifiableList(results);
	}

	public int getDealerWin() {
		return (int) results.stream()
				.map(Result::getResultType)
				.filter(ResultType::isLose)
				.count();
	}

	public int getDealerDraw() {
		return (int) results.stream()
				.map(Result::getResultType)
				.filter(ResultType::isDraw)
				.count();
	}

	public int getDealerLose() {
		return (int) results.stream()
				.map(Result::getResultType)
				.filter(ResultType::isWinOrBlackjackWin)
				.count();
	}

	public double getDealerMoney() {
		return -results.stream()
				.map(result -> result.getResultType().computeResultAmount(
						((Player) result.getPlayable()).getMoney().getAmount()))
				.reduce(Double::sum)
				.orElse(0d);
	}
}
