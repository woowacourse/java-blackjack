package blackjack.dto;

import java.util.List;

public record TotalWinningResult(
        int dealerWin,
        int dealerDraw,
        int dealerLoss,
        List<PlayerGameResult> playerGameResults
) {
}
