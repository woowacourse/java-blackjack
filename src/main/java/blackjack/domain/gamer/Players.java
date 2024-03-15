package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {
	private static final int MIN_SIZE = 1;
	private static final int MAX_SIZE = 8;
	private static final String SIZE_ERROR_MESSAGE =
		String.format("플레이어의 수는 %d 이상 또는 %d 이하이어야 합니다.", MIN_SIZE, MAX_SIZE);
	static final String DUPLICATION_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";

	private final List<Player> players;

	public Players(final List<Player> players) {
		this.players = players;
	}

	public static Players fromNames(final List<String> playerNames) {
		List<String> copyPlayerNames = new ArrayList<>(playerNames);
		validate(copyPlayerNames);

		List<Player> players = copyPlayerNames.stream()
			.map(Player::newInstance)
			.toList();
		return new Players(players);
	}

	private static void validate(final List<String> playerNames) {
		validateSize(playerNames);
		validateDuplication(playerNames);
	}

	private static void validateSize(final List<String> players) {
		if (players.size() < MIN_SIZE || players.size() > MAX_SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
		}
	}

	private static void validateDuplication(final List<String> players) {
		int distinctSize = new HashSet<>(players).size();

		if (players.size() != distinctSize) {
			throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
		}
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
