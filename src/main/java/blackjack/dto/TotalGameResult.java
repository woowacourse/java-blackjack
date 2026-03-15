package blackjack.dto;

import java.util.List;

public record TotalGameResult(
    DealerGameResult dealerGameResult,
    List<PlayerGameResult> playerGameResult
) {

    public static TotalGameResult of(DealerGameResult dealerGameResult, List<PlayerGameResult> playerGameResult) {
        return new TotalGameResult(dealerGameResult, playerGameResult);
    }
}
