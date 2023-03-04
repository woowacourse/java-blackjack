package blackjack.dto;

import static java.util.stream.Collectors.*;

import blackjack.domain.GameResult;
import java.util.List;

public class TotalGameResult {
    private final List<String> dealerGameResult;
    private final List<PlayerGameResult> playerGameResults;

    public TotalGameResult(List<String> dealerGameResult, List<PlayerGameResult> playerGameResults) {
        this.dealerGameResult = dealerGameResult;
        this.playerGameResults = playerGameResults;
    }

    public static TotalGameResult of(List<PlayerGameResult> playerGameResults) {
        List<String> dealerGameResult = playerGameResults.stream()
                .map(PlayerGameResult::getResult)
                .map(GameResult::getAntonym)
                .collect(toList());
        return new TotalGameResult(dealerGameResult, playerGameResults);
    }

    public List<String> getDealerGameResult() {
        return dealerGameResult;
    }

    public List<PlayerGameResult> getPlayerGameResults() {
        return playerGameResults;
    }
}
