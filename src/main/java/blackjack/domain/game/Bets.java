package blackjack.domain.game;

import blackjack.domain.card.Result;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.Map;

public final class Bets {
    private final Map<Player, Money> bets;

    public Bets() {
        bets = new HashMap<>();
    }

    public void addBet(final Player player, final int amount) {
        bets.put(player, Money.initialBet(amount));
    }

    public void calculateProfit(final Map<Player, Result> results) {
        for (Player player : results.keySet()) {
            final Result result = results.get(player);
            bets.computeIfPresent(player, (ignore, money) -> money.calculatePrize(result));
        }
    }

    public Map<Player, Money> getBets() {
        return new HashMap<>(bets);
    }

    public Money getDealerProfit() {
        return bets.values().stream()
                .reduce(Money.ZERO, Money::minus);
    }
}
