package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
	private static final String EMPTY_PLAYERS = "[ERROR] 한 명 이상의 플레이어가 필요합니다.";
	private static final String OVER_FULL_PLAYERS = "[ERROR] 7명 이상의 플레이어로는 원활한 진행이 불가합니다.";
	private static final int FULL_PLAYER_THRESHOLD = 7;

	private final List<Player> players;

	public Players(List<Player> players) {
		validateEmptyPlayers(players);
		validateFullPlayers(players);
		this.players = new ArrayList<>(players);
	}

	public static Players from(List<Name> names) {
		return new Players(names.stream()
			.map(Player::new)
			.collect(Collectors.toList()));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public boolean isAllPlayersBlackJackOrBust() {
		return this.players.size() == this.players.stream()
			.filter(player -> player.isBlackJack() || player.isBust())
			.count();
	}

	public int getPlayersSize() {
		return this.players.size();
	}

	private void validateEmptyPlayers(List<Player> players) {
		if (players.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYERS);
		}
	}

	private void validateFullPlayers(List<Player> players) {
		if (players.size() > FULL_PLAYER_THRESHOLD) {
			throw new IllegalArgumentException(OVER_FULL_PLAYERS);
		}
	}
}
