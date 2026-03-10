package dto.response;

import domain.PlayerWinningInfo;

public record PlayerWinningInfoResponse(String name, String winningCondition) {
    public static PlayerWinningInfoResponse from(PlayerWinningInfo playerWinningInfo) {
        return new PlayerWinningInfoResponse(playerWinningInfo.name(), playerWinningInfo.description());
    }
}
