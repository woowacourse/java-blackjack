package model.gameresult;

import java.util.Map;
import model.bettingamount.BettingAmounts;
import model.participant.Players;

public class GameResults {
    private final Map<String, GameResult> gameResults;

    public GameResults(final Map<String, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public GameResult getGameResultByName(final String name) {
        return gameResults.get(name);
    }

    public int calculateDealerResultCount(final GameResult gameResult) {
        return (int) gameResults.values().stream()
                .filter(value -> getOppositeResult(value).equals(gameResult))
                .count();
    }

    private GameResult getOppositeResult(final GameResult gameResult) {
        if (GameResult.WIN.equals(gameResult)) {
            return GameResult.LOSE;
        }
        if (GameResult.LOSE.equals(gameResult)) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public int calculateDealerProfit(final Players players, final BettingAmounts bettingAmounts) {
        return players.getNames().stream()
                .mapToInt(name -> bettingAmounts.findByName(name)
                        .getProfitByGameResult(getOppositeResult(gameResults.get(name))))
                .sum();
    }
}
