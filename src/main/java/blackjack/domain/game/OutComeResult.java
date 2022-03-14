package blackjack.domain.game;

import java.util.Map;

public class OutComeResult {

    private final Map<String, GameOutcome> playerResults;
    private final Map<GameOutcome, Integer> dealerResult;

    private OutComeResult(final Map<String, GameOutcome> playerResults,
                          final Map<GameOutcome, Integer> dealerResult) {
        this.playerResults = playerResults;
        this.dealerResult = dealerResult;
    }

    public static OutComeResult from(final Map<String, GameOutcome> playerResults) {
        final Map<GameOutcome, Integer> dealerResult = GameOutcome.createResultFormat();
        for (final GameOutcome gameOutcome : playerResults.values()) {
            dealerResult.merge(gameOutcome.reverse(), 1, Integer::sum);
        }
        return new OutComeResult(playerResults, dealerResult);
    }

    public Map<String, GameOutcome> getPlayerResults() {
        return playerResults;
    }

    public Map<GameOutcome, Integer> getDealerResult() {
        return dealerResult;
    }
}
