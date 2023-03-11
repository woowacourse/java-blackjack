package blackjack.domain.game;

import blackjack.domain.player.Name;
import blackjack.domain.player.Result;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Bets {
    private final Map<Name, Money> bets;

    public Bets() {
        bets = new LinkedHashMap<>();
    }

    public void addBets(final Map<Name, Money> bets) {
        this.bets.putAll(bets);
    }

    public void calculateProfit(final Map<Name, Result> results) {
        for (Name name : results.keySet()) {
            final Result result = results.get(name);
            bets.computeIfPresent(name, (ignore, money) -> money.calculatePrize(result));
        }
    }

    public Map<Name, Money> getBets() {
        return new LinkedHashMap<>(bets);
    }

    public Money getDealerProfit() {
        return bets.values().stream()
                .reduce(Money.ZERO, Money::minus);
    }
}
