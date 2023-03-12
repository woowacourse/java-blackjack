package blackjack.domain.game;

import blackjack.domain.player.Name;
import blackjack.domain.player.Result;
import java.util.HashMap;
import java.util.Map;

public final class Bets {
    private final Map<Name, Money> bets;

    public Bets() {
        bets = new HashMap<>();
    }

    public void addBet(final Name player, final int amount) {
        bets.put(player, Money.initialBet(amount));
    }

    public void calculateProfit(final Map<Name, Result> results) {
        for (Name name : results.keySet()) {
            final Result result = results.get(name);
            bets.computeIfPresent(name, (ignore, money) -> money.calculatePrize(result));
        }
    }

    public Map<Name, Money> getBets() {
        return new HashMap<>(bets);
    }

    public Money getDealerProfit() {
        return bets.values().stream()
                .reduce(Money.ZERO, Money::minus);
    }
}
