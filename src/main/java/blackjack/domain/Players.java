package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
	private static final String EMPTY_PLAYERS = "[ERROR] 한 명 이상의 플레이어가 필요합니다.";
	private final List<Player> players;

	public Players(List<Player> players) {
		validateEmptyPlayers(players);
		this.players = new ArrayList<>();
		this.players.addAll(players);
	}

	public static Players from(List<Name> names) {
		validateEmptyNames(names);
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

	private static void validateEmptyNames(List<Name> names) {
		if (names.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYERS);
		}
	}

	private void validateEmptyPlayers(List<Player> players) {
		if (players.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_PLAYERS);
		}
	}
}
