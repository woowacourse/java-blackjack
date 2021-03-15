package blackjack.domain.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Result {
    private final Map<Gambler, WinOrLose> gamblerResults = new LinkedHashMap<>();
    private final Dealer dealer;

    public Result(Dealer dealer) {
        this.dealer = dealer;
    }

    public void add(final Gambler gambler, final WinOrLose winOrLose) {
        gamblerResults.put(gambler, winOrLose);
    }

    public void calculateProfit() {
        gamblerResults.entrySet().forEach(entry -> {
            calculateGamblerProfit(entry);
            calculateDealerProfit(entry);
        });
    }

    private void calculateGamblerProfit(Entry<Gambler, WinOrLose> entry) {
        entry.getKey().calculateProfit(entry.getValue());
    }

    private void calculateDealerProfit(Entry<Gambler, WinOrLose> entry) {
        dealer.calculateProfit(entry.getKey());
    }

    public Map<Player, WinOrLose> getGamblerResult() {
        return Collections.unmodifiableMap(gamblerResults);
    }

    public Dealer getDealerInfo() {
        return dealer;
    }
}
