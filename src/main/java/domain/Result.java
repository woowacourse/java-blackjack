package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.user.Dealer;
import domain.user.Players;

public class Result {
	private final Map<String, Long> results;

	private Result(Map<String, Long> results) {
		this.results = results;
	}

	public static Result from(Dealer dealer, Players players) {
			List<String> results = new ArrayList<>();
			players.forEach(player -> results.add(ResultType.from(dealer, player).getName()));
			return new Result(
				results.stream()
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			);
	}

	public Map<String, Long> getResults() {
		return results;
	}
}
