package blackjack.dto;

import blackjack.domain.game.WinningType;
import java.util.List;

public record WinningState(
        List<PlayerWinningResult> playerWinningResults
) {

    public int getDealerStatistics(WinningType winningType) {
        return (int) playerWinningResults.stream()
                .filter(playerWinningResult -> playerWinningResult.winningType().equals(winningType.reverse()))
                .count();
    }
}
