package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = Collections.unmodifiableList(new ArrayList<>(players));
	}

	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}

	public List<String> getNames() {
		return players.stream()
				.map(Player::getName)
				.collect(Collectors.toList());
	}
}
