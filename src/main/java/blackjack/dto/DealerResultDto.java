package blackjack.dto;

import blackjack.domain.result.GameResult;
import java.util.List;

public record DealerResultDto(int win, int lose, int push) {
    public static DealerResultDto from(List<GameResultDtos> gameResultDtos) {
        int winCount = getPlayerWinCount(gameResultDtos);
        int loseCount = getPlayerLoseCount(gameResultDtos);
        int pushCount = getPushCount(gameResultDtos.size(), loseCount, winCount);
        return new DealerResultDto(winCount, loseCount, pushCount);
    }

    private static int getPushCount(int playerCount, int loseCount, int winCount) {
        return playerCount - loseCount - winCount;
    }

    private static int getPlayerLoseCount(List<GameResultDtos> gameResultDtos) {
        return (int) gameResultDtos.stream()
            .filter(gameResultDto -> gameResultDto.result() == GameResult.PLAYER_WIN)
            .count();
    }

    private static int getPlayerWinCount(List<GameResultDtos> gameResultDtos) {
        return (int) gameResultDtos.stream()
            .filter(gameResultDto -> gameResultDto.result() == GameResult.DEALER_WIN)
            .count();
    }
}
