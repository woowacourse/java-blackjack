package blackjack.domain.role;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PlayerTurns {

	private static final String EMPTY_PLAYER_ERROR = "더이상 진행할 플레이어가 없습니다.";

	private final Queue<Role> players;

	public PlayerTurns(List<Role> players) {
		this.players = new LinkedList<>(players);
	}

	public static PlayerTurns createEmpty() {
		return new PlayerTurns(Collections.emptyList());
	}

	public Role getCurrentPlayer() {
		if (players.isEmpty()) {
			throw new NoSuchElementException(EMPTY_PLAYER_ERROR);
		}
		return players.poll();
	}

	public Queue<Role> getPlayers() {
		return new LinkedList<>(players);
	}
}
