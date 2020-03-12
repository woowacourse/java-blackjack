package domain.result;

import domain.user.Player;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Result {
	private final Map<Player, ResultType> results;

	public Result(Map<Player, ResultType> results) {
		this.results = results;
	}

	public Map<ResultType, Long> createDealerResult() {
		return results.values().stream()
				.map(ResultType::opposite)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	public ResultType get(Player player) {
		return results.get(player);
	}
}
