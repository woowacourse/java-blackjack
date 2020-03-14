package domain.result;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class DealerResult {
	private static final long INIT_MATCH_COUNT = 0L;

	private final Map<MatchResult, Long> dealerResult;

	public DealerResult(List<MatchResult> playerMatchResults) {
		this.dealerResult = initDealerResult(new ArrayList<>(Objects.requireNonNull(playerMatchResults)));
	}

	private Map<MatchResult, Long> initDealerResult(List<MatchResult> playerMatchResults) {
		Map<MatchResult, Long> result = playerMatchResults.stream()
			.map(MatchResult::reverseWinAndLose)
			.collect(groupingBy(Function.identity(), counting()));

		Arrays.stream(MatchResult.values())
			.forEach(key -> result.putIfAbsent(key, INIT_MATCH_COUNT));
		return Collections.unmodifiableMap(result);
	}

	public Long getResultCount(MatchResult matchResult) {
		return dealerResult.get(matchResult);
	}
}
