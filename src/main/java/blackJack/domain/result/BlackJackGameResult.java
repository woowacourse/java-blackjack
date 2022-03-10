package blackJack.domain.result;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public class BlackJackGameResult {

    private final Map<Player, WinOrLose> gameResult;

    private BlackJackGameResult(Map<Player, WinOrLose> gameResult) {
        this.gameResult = gameResult;
    }

    public static BlackJackGameResult ofGameResult(Dealer dealer, List<Player> players) {
        final Map<Player, WinOrLose> gameResult = new LinkedHashMap<>();

        for (Player player : players) {
            final WinOrLose winOrLose = WinOrLose.calculateWinOrLose(player.getScore(), dealer.getScore());
            gameResult.put(player, winOrLose);
        }

        return new BlackJackGameResult(gameResult);
    }

    public Map<WinOrLose, Integer> calculateDealerResult() {
        final Map<WinOrLose, Integer> dealerGameScore = getWinOrLose();

        for (WinOrLose value : gameResult.values()) {
            dealerGameScore.computeIfPresent(value, (k, v) -> v + 1);
        }
        swapResult(dealerGameScore);

        return dealerGameScore;
    }

    private Map<WinOrLose, Integer> getWinOrLose() {
        final Map<WinOrLose, Integer> dealerGameScore = new LinkedHashMap<>();

        for (WinOrLose value : WinOrLose.values()) {
            dealerGameScore.put(value, 0);
        }

        return dealerGameScore;
    }

    private void swapResult(Map<WinOrLose, Integer> dealerGameScore) {
        final int loseCounts = dealerGameScore.get(WinOrLose.WIN);
        dealerGameScore.put(WinOrLose.WIN, dealerGameScore.get(WinOrLose.LOSE));
        dealerGameScore.put(WinOrLose.LOSE, loseCounts);
    }

    public Map<Player, WinOrLose> getGameResult() {
        return gameResult;
    }
}
