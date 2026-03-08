package blackjack.dto;

import java.util.List;

public record WinningResult(
        int dealerWin,
        int dealerLoss,
        List<PlayerResult> playerResults
) {
}
