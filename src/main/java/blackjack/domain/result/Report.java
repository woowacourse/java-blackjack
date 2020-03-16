package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
		validateUser(dealer, players);
		return new Report(
			generateDealerResult(dealer, players),
			generatePlayersResult(dealer, players));
	}

	private static void validateUser(Dealer dealer, List<Player> players) {
		if (Objects.isNull(dealer) || Objects.isNull(players) || players.isEmpty()) {
			throw new InvalidReportException(InvalidReportException.EMPTY);
		}
	}

	private static Map<Player, ResultType> generatePlayersResult(Dealer dealer, List<Player> players) {
		return players.stream()
			.collect(collectingAndThen(
				toMap(Function.identity(), player -> ResultType.of(player, dealer)),
				LinkedHashMap::new));
	}

	private static Map<ResultType, Long> generateDealerResult(Dealer dealer, List<Player> players) {
		return players.stream()
			.map(player -> ResultType.of(dealer, player))
			.collect(collectingAndThen(groupingBy(Function.identity(), counting()), EnumMap::new));
	}

	public Map<ResultType, Long> getDealerResult() {
		return dealerResult;
	}

	public Map<Player, ResultType> getPlayersResult() {
		return playersResult;
	}
}
