package dto;

import domain.game.WinningStatus;

public record PlayerResultInfo(
        String name,
        WinningStatus winningStatus
) {
}
