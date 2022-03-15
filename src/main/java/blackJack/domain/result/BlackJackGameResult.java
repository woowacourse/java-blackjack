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

    public Map<WinDrawLose, Integer> calculateDealerResult() {
        final Map<WinDrawLose, Integer> gameResult = initializeDealerResult();
        for (Player player : players) {
            final WinDrawLose winDrawLose = dealer.isWin(player);
            gameResult.computeIfPresent(winDrawLose, (k, v) -> v + 1);
        }
        return gameResult;
    }

    private Map<WinDrawLose, Integer> initializeDealerResult() {
        final Map<WinDrawLose, Integer> gameResult = new EnumMap<>(WinDrawLose.class);
        for (WinDrawLose value : WinDrawLose.values()) {
            gameResult.put(value, 0);
        }
        return gameResult;
    }

    public Map<Player, WinDrawLose> calculatePlayersResult() {
        final Map<Player, WinDrawLose> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            final WinDrawLose winOrLose = player.isWin(dealer);
            gameResult.put(player, winOrLose);
        }
        return gameResult;
    }
}
