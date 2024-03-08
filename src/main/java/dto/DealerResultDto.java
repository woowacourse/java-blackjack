package dto;

import domain.Player;
import domain.Result;

import java.util.Map;

public record DealerResultDto(int winCount, int lossCount, int pushCount) {

    public static DealerResultDto from(Map<Player, Result> gameResult) {
        int winCount = countResult(gameResult, Result.DEALER_WIN);
        int lossCount = countResult(gameResult, Result.PLAYER_WIN);
        int pushCount = countResult(gameResult, Result.PUSH);

        return new DealerResultDto(winCount, lossCount, pushCount);
    }

    private static int countResult(Map<Player, Result> gameResult, Result status) { //TODO: 네이밍 고민
        return (int) gameResult.values().stream()
                .filter(result -> result == status).
                count();
    }
}
