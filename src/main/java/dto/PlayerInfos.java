package dto;

import domain.participant.Player;

import java.util.List;

public record PlayerInfos(List<PlayerInfo> playerInfos) {
    public static PlayerInfos from(final List<Player> players) {
        return new PlayerInfos(players.stream()
                .map(PlayerInfo::from)
                .toList());
    }
}
