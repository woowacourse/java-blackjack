package domains.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domains.card.Deck;

public class Players implements Iterable<Player> {
	private static final String DELIMITER = ",";

	private List<Player> players;

	public Players(String playerNames, Deck deck) {
		players = new ArrayList<>();
		checkNull(playerNames);
		List<String> names = splitNames(playerNames);
		checkDuplication(names);
		for (String name : names) {
			players.add(new Player(name, deck));
		}
	}

	public Players(List<Player> players) {
		this.players = players;
	}

	private void checkNull(String playerNames) {
		if (Objects.isNull(playerNames)) {
			throw new InvalidPlayersException(InvalidPlayersException.NULL);
		}
	}

	private List<String> splitNames(String playerNames) {
		List<String> names = Arrays.asList(playerNames.split(DELIMITER));
		names = names.stream().map(String::trim).collect(Collectors.toList());
		return names;
	}

	private void checkDuplication(List<String> names) {
		int distinctNameCount = (int)names.stream()
			.distinct()
			.count();

		if (names.size() != distinctNameCount) {
			throw new InvalidPlayersException(InvalidPlayersException.DUPLICATION);
		}
	}

	@Override
	public Iterator<Player> iterator() {
		return players.iterator();
	}
}
