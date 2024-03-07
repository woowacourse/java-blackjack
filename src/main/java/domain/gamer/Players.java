package domain.gamer;

import java.util.ArrayList;
import java.util.List;

public class Players {
	public static final String SIZE_ERROR_MESSAGE = "플레이어의 수는 1 이상 또는 8 이하이어야 합니다.";
	public static final String DUPLICATION_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";

	private final List<Player> players;

	public Players(List<String> players) {
		// TODO : 메소드 분리 및 매직넘버 상수화, 예외 메시지, Collection 방어적 복사
		if (players.size() < 1 || players.size() > 8) {
			throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
		}

		long distinctCount = players.stream()
			.distinct().count();
		if (players.size() != distinctCount) {
			throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
		}

		this.players = players.stream()
			.map(player -> new Player(player, new ArrayList<>()))
			.toList();
	}

	public List<Player> getPlayers() {
		return players;
	}
}
