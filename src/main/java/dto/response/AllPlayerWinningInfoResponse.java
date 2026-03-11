package dto.response;

import domain.game_result.vo.PlayerWinningInfo;
import java.util.List;

public record AllPlayerWinningInfoResponse(List<PlayerWinningInfoResponse> playerWinningInfoResponses) {
    public static AllPlayerWinningInfoResponse of(List<PlayerWinningInfo> playerWinningInfo) {
        return new AllPlayerWinningInfoResponse(parse(playerWinningInfo));
    }

    private static List<PlayerWinningInfoResponse> parse(List<PlayerWinningInfo> playerWinningInfo) {
        return playerWinningInfo.stream()
                .map(PlayerWinningInfoResponse::from)
                .toList();
    }
}
