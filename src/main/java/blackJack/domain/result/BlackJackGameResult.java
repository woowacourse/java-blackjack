package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {

    private final Map<Player, WinDrawLose> gameResult;

    private BlackJackGameResult(Map<Player, WinDrawLose> gameResult) {
        this.gameResult = gameResult;
    }

    public static BlackJackGameResult ofGameResult(Dealer dealer, List<Player> players) {
        final Map<Player, WinDrawLose> gameResult = new LinkedHashMap<>();

        for (Player player : players) {
            final WinDrawLose winOrLose = WinDrawLose.calculateWinDrawLose(player.getScore(), dealer.getScore());
            gameResult.put(player, winOrLose);
        }

        return new BlackJackGameResult(gameResult);
    }

    public Map<WinDrawLose, Integer> calculateDealerResult() {
        final Map<WinDrawLose, Integer> dealerGameScore = getWinOrLose();

        for (WinDrawLose value : gameResult.values()) {
            dealerGameScore.computeIfPresent(value, (k, v) -> v + 1);
        }
        swapResult(dealerGameScore);

        return dealerGameScore;
    }

    private Map<WinDrawLose, Integer> getWinOrLose() {
        final Map<WinDrawLose, Integer> dealerGameScore = new EnumMap<>(WinDrawLose.class);

        for (WinDrawLose value : WinDrawLose.values()) {
            dealerGameScore.put(value, 0);
        }

        return dealerGameScore;
    }

    private void swapResult(Map<WinDrawLose, Integer> dealerGameScore) {
        final int loseCounts = dealerGameScore.get(WinDrawLose.WIN);
        dealerGameScore.put(WinDrawLose.WIN, dealerGameScore.get(WinDrawLose.LOSE));
        dealerGameScore.put(WinDrawLose.LOSE, loseCounts);
    }

    public Map<Player, WinDrawLose> getGameResult() {
        return gameResult;
    }
}
