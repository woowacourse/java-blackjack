package blackjack.dto.gamer;

import java.util.List;

import blackjack.domain.gamer.Players;

public record PlayerInfos(List<PlayerInfo> playerInfos) {

	public static PlayerInfos from(final Players players) {
		return new PlayerInfos(players.getPlayers().stream()
			.map(PlayerInfo::from)
			.toList());
	}
}
