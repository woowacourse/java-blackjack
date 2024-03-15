package dto;

import domain.Player;
import domain.Result;

import java.util.Map;

public record DealerWinLossDto(int winCount, int lossCount, int pushCount) {

    public static DealerWinLossDto from(Map<Player, Result> gameResult) {
        int winCount = count(gameResult, Result.DEALER_WIN);
        int lossCount = count(gameResult, Result.PLAYER_WIN);
        int pushCount = count(gameResult, Result.PUSH);

        return new DealerWinLossDto(winCount, lossCount, pushCount);
    }

    private static int count(Map<Player, Result> gameResult, Result status) {
        return (int) gameResult.values().stream()
                .filter(result -> result == status)
                .count();
    }
}
