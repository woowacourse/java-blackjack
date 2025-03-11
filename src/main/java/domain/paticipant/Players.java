package domain.paticipant;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
	private final List<Player> players;

	public Players(final List<String> names) {
		players = names.stream()
			.map(Player::new)
			.collect(Collectors.toList());
	}

	public List<Player> getPlayers() {
		return players;
	}
}
