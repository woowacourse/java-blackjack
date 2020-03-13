package domain.result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DealerResult {
	private static final long INIT_MATCH_COUNT = 0L;

	private final List<MatchResult> playerMatchResults;

	public DealerResult(List<MatchResult> playerMatchResults) {
		this.playerMatchResults = Objects.requireNonNull(playerMatchResults);
	}

	public Map<MatchResult, Long> calculateDealerResult() {
		Map<MatchResult, Long> result = playerMatchResults.stream()
			.map(MatchResult::reverseWinAndLose)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Arrays.stream(MatchResult.values())
			.forEach(key -> result.putIfAbsent(key, INIT_MATCH_COUNT));

		return result;
	}
}
