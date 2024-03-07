package domain.gamer;

import java.util.List;

public class Players {
	private static final int MIN_SIZE = 1;
	private static final int MAX_SIZE = 8;
	private static final String SIZE_ERROR_MESSAGE =
		String.format("플레이어의 수는 %d 이상 또는 %d 이하이어야 합니다.", MIN_SIZE, MAX_SIZE);
	static final String DUPLICATION_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";

	private final List<Player> players;

	public Players(List<String> players) {
		// TODO : 메소드 분리, 예외 메시지, Collection 방어적 복사
		if (players.size() < MIN_SIZE || players.size() > MAX_SIZE) {
			throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
		}

		long distinctCount = players.stream()
			.distinct().count();
		if (players.size() != distinctCount) {
			throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
		}

		this.players = players.stream()
			.map(Player::newInstance)
			.toList();
	}

	public List<Player> getPlayers() {
		return players;
	}
}
