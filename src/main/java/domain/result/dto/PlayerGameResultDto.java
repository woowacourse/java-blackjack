package domain.result.dto;

import domain.participant.Player;
import domain.result.WinningStatus;

public record PlayerGameResultDto(String playerName, WinningStatus winningStatus) {
    public static PlayerGameResultDto of(Player player, WinningStatus winningStatus) {
        return new PlayerGameResultDto(player.toDisplayMyName(), winningStatus);
    }
}
