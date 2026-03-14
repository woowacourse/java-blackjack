package blackjack.dto;

import blackjack.domain.result.GameResult;
import blackjack.domain.result.PlayerResults;

public record DealerResultDto(int win, int lose, int push) {
    public static DealerResultDto from(PlayerResults results) {
        int winCount = getPlayerWinCount(results);
        int loseCount = getPlayerLoseCount(results);
        int pushCount = getPushCount(results.entries().size(), loseCount, winCount);
        return new DealerResultDto(winCount, loseCount, pushCount);
    }

    private static int getPlayerWinCount(PlayerResults results) {
        return (int) results.entries().values().stream()
            .filter(result -> result == GameResult.DEALER_WIN)
            .count();
    }

    private static int getPlayerLoseCount(PlayerResults results) {
        return (int) results.entries().values().stream()
            .filter(result -> result == GameResult.PLAYER_WIN)
            .count();
    }

    private static int getPushCount(int playerCount, int loseCount, int winCount) {
        return playerCount - loseCount - winCount;
    }
}
