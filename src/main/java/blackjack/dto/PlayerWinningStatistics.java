package blackjack.dto;

import blackjack.domain.GameResultType;
import java.util.List;

public class PlayerWinningStatistics {

    private final List<PlayerWinningResult> playerWinningResults;

    public PlayerWinningStatistics(List<PlayerWinningResult> playerWinningResults) {
        this.playerWinningResults = playerWinningResults;
    }

    public int getDealerStatistics(GameResultType gameResultType) {
        return (int) playerWinningResults.stream()
                .filter(playerWinningResult -> playerWinningResult.getResult().equals(gameResultType.reverse()))
                .count();
    }

    public List<PlayerWinningResult> getPlayerWinningResults() {
        return playerWinningResults;
    }
}
