package blackjack.dto;

import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;
import java.util.Map;

public record DealerResultDto(int win, int lose, int push) {
    public static DealerResultDto from(Map<Player, GameResult> results) {
        int winCount = getPlayerWinCount(results);
        int loseCount = getPlayerLoseCount(results);
        int pushCount = getPushCount(results.size(), loseCount, winCount);
        return new DealerResultDto(winCount, loseCount, pushCount);
    }

    private static int getPlayerWinCount(Map<Player, GameResult> results) {
        return (int) results.values().stream()
            .filter(result -> result == GameResult.DEALER_WIN)
            .count();
    }

    private static int getPlayerLoseCount(Map<Player, GameResult> results) {
        return (int) results.values().stream()
            .filter(result -> result == GameResult.PLAYER_WIN)
            .count();
    }

    private static int getPushCount(int playerCount, int loseCount, int winCount) {
        return playerCount - loseCount - winCount;
    }
}
