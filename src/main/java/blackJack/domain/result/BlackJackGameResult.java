package blackJack.domain.result;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public class BlackJackGameResult {

    private final Map<Player, OutCome> gameResult;

    private BlackJackGameResult(Map<Player, OutCome> gameResult) {
        this.gameResult = gameResult;
    }

    public static BlackJackGameResult ofGameResult(Dealer dealer, List<Player> players) {
        final Map<Player, OutCome> gameResult = new LinkedHashMap<>();

        for (Player player : players) {
            final OutCome winOrLose = OutCome.calculatePlayerWinDrawLose(player, dealer);
            gameResult.put(player, winOrLose);
        }

        return new BlackJackGameResult(gameResult);
    }

    public Map<OutCome, Integer> calculateDealerResult() {
        final Map<OutCome, Integer> dealerGameScore = getWinOrLose();

        for (OutCome value : gameResult.values()) {
            dealerGameScore.computeIfPresent(value, (k, v) -> v + 1);
        }
        swapResult(dealerGameScore);

        return dealerGameScore;
    }

    private Map<OutCome, Integer> getWinOrLose() {
        final Map<OutCome, Integer> dealerGameScore = new LinkedHashMap<>();

        for (OutCome value : OutCome.values()) {
            dealerGameScore.put(value, 0);
        }

        return dealerGameScore;
    }

    private void swapResult(Map<OutCome, Integer> dealerGameScore) {
        final int loseCounts = dealerGameScore.get(OutCome.WIN);
        dealerGameScore.put(OutCome.WIN, dealerGameScore.get(OutCome.LOSE));
        dealerGameScore.put(OutCome.LOSE, loseCounts);
    }

    public Map<Player, OutCome> getGameResult() {
        return gameResult;
    }
}
