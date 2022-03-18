package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
	public static final String EMPTY_PLAYER_EXCEPTION = "플레이어를 한명 이상 입력해야 합니다.";
	public static final String DUPLICATE_PLAYER_EXCEPTION = "이름에 중복이 있으면 안됩니다.";
	private final List<Player> players;

	public Players(final List<Player> players) {
		validateNoPlayer(players);
		validateDuplicateName(players);
		this.players = new ArrayList<>(players);
	}

	private void validateNoPlayer(List<Player> players) {
		if (players.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYER_EXCEPTION);
		}
	}

	private void validateDuplicateName(final List<Player> players) {
		final int count = countPlayers(players);
		if (count != players.size()) {
			throw new IllegalArgumentException(DUPLICATE_PLAYER_EXCEPTION);
		}
	}

	private int countPlayers(List<Player> players) {
		return (int)players.stream()
			.map(Gamer::getName)
			.distinct()
			.count();
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
