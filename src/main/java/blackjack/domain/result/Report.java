package blackjack.domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
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

	// TODO: 2020-03-17 일급컬렉션으로 감춘 값을 그대로 내보내지 마라 -> ResultType을 String으로!
	public Map<ResultType, Long> getDealerResult() {
		return dealerResult;
	}

	public Map<Player, ResultType> getPlayersResult() {
		return playersResult;
	}
}
