package blackjack.domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Compete {

	private final Map<String, Outcome> competeResults;

	public Compete() {
		this.competeResults = new HashMap<>();
	}

	public void judgeCompete(Role player, Role dealer) {
		Outcome result = Outcome.of(player.calculateFinalScore(), dealer.calculateFinalScore());
		competeResults.put(player.getName(), result);
	}

	public Map<Outcome, Long> getDealerCompeteResults() {
		return competeResults.values().stream()
				.collect(Collectors.groupingBy(Outcome::getOppositeOutcome, () -> new EnumMap<>(Outcome.class),
						Collectors.counting()));
	}

	public Outcome getPlayerCompeteResults(Role player) {
		return competeResults.get(player.getName());
	}
}
