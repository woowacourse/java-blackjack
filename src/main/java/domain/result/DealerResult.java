package domain.result;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DealerResult {
	private final List<MatchResult> playerMatchResults;

	public DealerResult(List<MatchResult> playerMatchResults) {
		this.playerMatchResults = Objects.requireNonNull(playerMatchResults);
	}

	public Map<MatchResult, Long> calculateDealerResult() {
		return playerMatchResults.stream()
			.map(MatchResult::switching)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}


	public String getResult(){
		StringBuilder builder = new StringBuilder("딜러: ");
		Map<MatchResult, Long> map = calculateDealerResult();
		for (MatchResult matchResult : MatchResult.values()) {
			builder.append(map.get(matchResult) + matchResult.getMatchResult());
		}
		return builder.toString();
	}
}
