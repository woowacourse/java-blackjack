package blackjack.domain.game;

import java.util.Map;

public class OutComeResult {

    private final Map<String, GameOutcome> playerResults;
    private final Map<GameOutcome, Integer> dealerResult;

    public OutComeResult(final Map<String, GameOutcome> playerResults) {
        this.playerResults = playerResults;
        this.dealerResult = convertToDealerResult(playerResults);
    }

    private Map<GameOutcome, Integer> convertToDealerResult(final Map<String, GameOutcome> playerResults) {
        final Map<GameOutcome, Integer> dealerResult = GameOutcome.createResultFormat();
        for (final GameOutcome gameOutcome : playerResults.values()) {
            dealerResult.merge(gameOutcome.reverse(), 1, Integer::sum);
        }
        return dealerResult;
    }

    public Map<String, GameOutcome> getPlayerResults() {
        return playerResults;
    }

    public Map<GameOutcome, Integer> getDealerResult() {
        return dealerResult;
    }
}
