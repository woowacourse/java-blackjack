package blackjack.domain.gamer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

	private static final int MIN_PLAYER_COUNT = 2;
	private static final int MAX_PLAYER_COUNT = 8;

	private final List<Player> players;

	public Players(List<String> names) {
		validateNames(names);

		this.players = names.stream()
			.map(name -> new Player(new Name(name)))
			.toList();
	}

	private void validateNames(List<String> names) {
		validateDuplicate(names);
		validateCount(names.size());
	}

	private void validateCount(int count) {
		if (count < MIN_PLAYER_COUNT || count > MAX_PLAYER_COUNT) {
			throw new IllegalArgumentException();
		}
	}

	private void validateDuplicate(List<String> names) {
		Set<String> nonDuplicateNames = new HashSet<>(names);

		if (names.size() != nonDuplicateNames.size()) {
			throw new IllegalArgumentException();
		}
	}
}
