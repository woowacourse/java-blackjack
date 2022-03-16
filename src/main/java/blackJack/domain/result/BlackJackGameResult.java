package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {

    private final Dealer dealer;
    private final List<Player> players;

    public BlackJackGameResult(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Integer> calculateDealerResult() {
        final Map<String, Integer> gameResult = initializeDealerResult();
        for (Player player : players) {
            final WinDrawLose playerWinDrawLose = WinDrawLose.calculateWinDrawLose(player, dealer);
            final String winDrawLoseResult = WinDrawLose.swapResult(playerWinDrawLose).getResult();
            gameResult.computeIfPresent(winDrawLoseResult, (k, v) -> v + 1);
        }
        return gameResult;
    }

    private Map<String, Integer> initializeDealerResult() {
        final Map<String, Integer> gameResult = new LinkedHashMap<>();
        for (WinDrawLose winDrawLose : WinDrawLose.values()) {
            gameResult.put(winDrawLose.getResult(), 0);
        }
        return gameResult;
    }

    public Map<Player, WinDrawLose> calculatePlayersResult() {
        final Map<Player, WinDrawLose> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            final WinDrawLose winOrLose = WinDrawLose.calculateWinDrawLose(player, dealer);
            gameResult.put(player, winOrLose);
        }
        return gameResult;
    }
}
