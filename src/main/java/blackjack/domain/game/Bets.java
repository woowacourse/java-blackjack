package blackjack.domain.game;

import blackjack.domain.player.Player;
import blackjack.domain.player.Result;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bets {
    private final Map<Player, Money> bets;

    public Bets(final Map<Player, Money> initialBets) {
        bets = new LinkedHashMap<>(initialBets);
    }

    public void calculateProfit(final Map<Player, Result> results) {
        for (Player player : results.keySet()) {
            final Result result = results.get(player);
            bets.computeIfPresent(player, (ignore, money) -> money.calculatePrize(result));
        }
    }

    public Map<Player, Money> getBets() {
        return new LinkedHashMap<>(bets);
    }

    public Money getDealerProfit() {
        return bets.values().stream()
                .reduce(Money.ZERO, Money::minus);
    }

}
