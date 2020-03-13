package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public class Report {
	private final Map<ResultType, Long> dealerResult;
	private final Map<Player, ResultType> playersResult;

	private Report(Map<ResultType, Long> dealerResult, Map<Player, ResultType> playersResult) {
		this.dealerResult = dealerResult;
		this.playersResult = playersResult;
	}

	public static Report from(Dealer dealer, List<Player> players) {
		return new Report(
			generateDealerResult(dealer, players),
			generatePlayersResult(dealer, players));
	}

	private static Map<Player, ResultType> generatePlayersResult(Dealer dealer, List<Player> players) {
		return players.stream()
			.collect(toMap(Function.identity(), player -> ResultType.from(player, dealer)));
	}

	private static Map<ResultType, Long> generateDealerResult(Dealer dealer, List<Player> players) {
		return players.stream()
			.map(player -> ResultType.from(dealer, player))
			.collect(groupingBy(Function.identity(), counting()));
	}
}
