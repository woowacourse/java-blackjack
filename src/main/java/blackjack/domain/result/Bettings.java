package blackjack.domain.result;

import blackjack.domain.gamers.Name;
import blackjack.domain.gamers.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Bettings {

    private static final int NORMAL_WIN_PROFIT_MULTIPLIER = 1;
    private static final double BLACKJACK_WIN_PROFIT_MULTIPLIER = 1.5;
    private static final int PUSH_PROFIT_MULTIPLIER = 0;

    private final Map<Player, Money> bettings;

    public Bettings(final Map<Player, Money> bettings) {
        this.bettings = bettings;
    }

    public Map<Name, Money> calculatePlayerProfits(final Judge judge) {
        return bettings.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> calculateProfit(entry.getValue(), judge.calculatePlayerOutcome(entry.getKey())),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private Money calculateProfit(final Money betting, final PlayerOutcome playerOutcome) {
        if (playerOutcome == PlayerOutcome.BLACKJACK_WIN) {
            return betting.multiply(BLACKJACK_WIN_PROFIT_MULTIPLIER);
        }
        if (playerOutcome == PlayerOutcome.NORMAL_WIN) {
            return betting.multiply(NORMAL_WIN_PROFIT_MULTIPLIER);
        }
        if (playerOutcome == PlayerOutcome.PUSH) {
            return betting.multiply(PUSH_PROFIT_MULTIPLIER);
        }
        return betting.toNegative();
    }

    public Money calculateDealerProfit(final Judge judge) {
        final double playerProfitsSum = calculatePlayerProfits(judge).values()
                .stream()
                .mapToDouble(Money::value)
                .sum();
        return new Money(playerProfitsSum).toNegative();
    }
}
