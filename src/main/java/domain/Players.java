package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players {

	private final List<Participant> players;

	public Players(List<String> names, List<List<Card>> initCards) {
		this.players = IntStream.range(0, names.size())
			.mapToObj(i -> new Participant(names.get(i), initCards.get(i)))
			.collect(Collectors.toList());
	}

	public List<Integer> getBestScores() {
		return players.stream().map(Participant::getBestScore).collect(Collectors.toList());
	}

	public List<Participant> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	public Map<String, Versus> compare(Participant other) {
		Map<String, Versus> map = new LinkedHashMap<>();
		players.stream().forEach(participant -> map.put(participant.name, participant.compare(other)));
		return map;
	}
}
