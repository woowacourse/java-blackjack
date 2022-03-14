package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
	private static final String EMPTY_PLAYERS = "[ERROR] 한 명 이상의 플레이어가 필요합니다.";
	private final List<Player> players;

	public Players(List<Name> playerNames) {
		validateEmptyNames(playerNames);
		players = new ArrayList<>();
		for (Name playerName : playerNames) {
			players.add(new Player(playerName));
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	public boolean isAllPlayersBlackJackOrBust() {
		return this.players.size() == this.players.stream()
			.filter(player -> player.isBlackJack() || player.isBust())
			.count();
	}

	private void validateEmptyNames(List<Name> names) {
		if (names.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYERS);
		}
	}
}
