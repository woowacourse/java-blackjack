package blackjack.dto;

import blackjack.domain.gamer.GameResult;
import java.util.List;

public record DealerResultDto(int winCount, int loseCount) {

    public static DealerResultDto fromPlayerResults(List<GameResult> playerResults) {
        int dealerWinCount = (int) playerResults.stream()
                .filter(GameResult::isLose)
                .count();

        int dealerLoseCount = playerResults.size() - dealerWinCount;

        return new DealerResultDto(dealerWinCount, dealerLoseCount);
    }
}
