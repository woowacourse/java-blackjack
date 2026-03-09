package blackjack.dto;

import java.util.List;

public record WinningResult(
        int dealerLoss,
        int dealerWin,
        List<PlayerResult> playerResults
) {
}
