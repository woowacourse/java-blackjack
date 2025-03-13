package model.gameresult;

import java.util.Map;
import model.bettingamount.BettingAmount;
import model.participant.Player;
import model.participant.Players;

public class GameResults {
    private final Map<String, GameResult> gameResults;

    public GameResults(final Map<String, GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public GameResult getGameResultByName(final String name) {
        return gameResults.get(name);
    }

    public int calculatePlayerProfit(Player player) {
        GameResult gameResult = getGameResultByName(player.getName());
        BettingAmount bettingAmount = player.getBettingAmount();
        return bettingAmount.getProfitByGameResult(gameResult);
    }

    public int calculateDealerProfit(final Players players) {
        return -1 * players.getPlayers().stream()
                .mapToInt(this::calculatePlayerProfit)
                .sum();
    }
}
