package blackjack.dto;

import java.util.List;

public record TotalGameResult(
    DealerGameResult dealerGameResult,
    List<PlayerGameResult> playerGameResult
) {

}
