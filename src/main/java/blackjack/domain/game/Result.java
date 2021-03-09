package blackjack.domain.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private final Map<Player, WinOrLose> gamblerResults = new LinkedHashMap<>();
    private final Dealer dealer;

    public Result(Dealer dealer) {
        this.dealer = dealer;
    }

    public void add(final Player player, final WinOrLose winOrLose) {
        gamblerResults.put(player, winOrLose);
    }

    public int countDealerWin() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.LOSE)
                .count();
    }

    public int countDealerLose() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.WIN)
                .count();
    }

    public int countDealerDraw() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.DRAW)
                .count();
    }

    public Map<Player, WinOrLose> getGamblerResult() {
        return Collections.unmodifiableMap(gamblerResults);
    }

    public Dealer getDealerInfo() {
        return dealer;
    }
}
