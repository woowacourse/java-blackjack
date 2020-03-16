package blackjack.domain.result;

import static java.util.stream.Collectors.*;

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
			.collect(collectingAndThen(groupingBy(Function.identity(), counting()), EnumMap::new));
	}

	private static Map<Player, ResultType> generatePlayersResult(BlackjackTable blackjackTable) {
		return blackjackTable.getPlayers().stream()
			.collect(collectingAndThen(
				toMap(Function.identity(), player -> ResultType.of(player, blackjackTable.getDealer())),
				LinkedHashMap::new));
	}

	public Map<ResultType, Long> getDealerResult() {
		return dealerResult;
	}

	public Map<Player, ResultType> getPlayersResult() {
		return playersResult;
	}
}
