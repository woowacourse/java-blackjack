package domain.result;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DealerResult {
	private static final long INIT_MATCH_COUNT = 0L;

	private final List<UserResult> playerResults;

	public DealerResult(List<UserResult> playerResults) {
		this.playerResults = Objects.requireNonNull(playerResults);
	}

	public Map<MatchResult, Long> reversePlayersResult() {
		Map<MatchResult, Long> result = playerResults.stream()
			.map(UserResult::getReverseResult)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Arrays.stream(MatchResult.values())
			.forEach(key -> result.putIfAbsent(key, INIT_MATCH_COUNT));

		return result;
	}
}
