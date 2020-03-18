package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import blackjack.domain.blackjack.BlackjackTable;
import blackjack.domain.exceptions.InvalidReportException;
import blackjack.domain.user.Player;

public class Report {
	private final Map<ResultType, Long> dealerResult;
	private final Map<Player, ResultType> playersResult;

	private Report(Map<ResultType, Long> dealerResult, Map<Player, ResultType> playersResult) {
		this.dealerResult = dealerResult;
		this.playersResult = playersResult;
	}

	public static Report from(BlackjackTable blackjackTable) {
		return Optional.ofNullable(blackjackTable)
			.map(value -> new Report(generateDealerResult(value), generatePlayersResult(value)))
			.orElseThrow(() -> new InvalidReportException(InvalidReportException.EMPTY));
	}

	private static Map<ResultType, Long> generateDealerResult(BlackjackTable blackjackTable) {
		return blackjackTable.getPlayers().stream()
			.map(player -> ResultType.of(blackjackTable.getDealer(), player))
			.collect(collectingAndThen(
				groupingBy(Function.identity(), counting()),
				groupingMap -> Collections.unmodifiableMap(new EnumMap<>(groupingMap))));
	}

	private static Map<Player, ResultType> generatePlayersResult(BlackjackTable blackjackTable) {
		return blackjackTable.getPlayers().stream()
			.collect(collectingAndThen(
				toMap(Function.identity(),
					player -> ResultType.of(player, blackjackTable.getDealer()),
					(x, y) -> x,
					LinkedHashMap::new),
				Collections::unmodifiableMap));
	}

	public List<String> getDealerResult() {
		return dealerResult.entrySet().stream()
			.map(entry -> {
				long count = entry.getValue();
				ResultType resultType = entry.getKey();
				return count + resultType.getAlias();
			})
			.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}

	public Map<Player, String> getPlayersResult() {
		return playersResult.keySet().stream()
			.collect(collectingAndThen(
				toMap(Function.identity(),
					player -> playersResult.get(player).getAlias(),
					(x, y) -> x,
					LinkedHashMap::new),
				Collections::unmodifiableMap));
	}
}
