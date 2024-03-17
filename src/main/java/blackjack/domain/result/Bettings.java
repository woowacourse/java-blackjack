package blackjack.domain.result;

import blackjack.domain.gamers.Name;
import blackjack.domain.gamers.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bettings {

    private final Map<Player, Money> bettings;

    public Bettings(final Map<Player, Money> bettings) {
        this.bettings = bettings;
    }

    public Map<Name, Money> calculatePlayerProfits(final Judge judge) {
        final Map<Name, Money> playerProfits = new LinkedHashMap<>();
        bettings.forEach((player, betting) -> {
            final PlayerOutcome playerOutcome = judge.calculatePlayerOutcome(player);
            playerProfits.put(player.getName(), betting.multiply(playerOutcome.getProfitMultiplier()));
        });

        return playerProfits;
    }

    public Money calculateDealerProfit(final Judge judge) {
        final double playerProfitsSum = calculatePlayerProfits(judge).values()
                .stream()
                .mapToDouble(Money::value)
                .sum();
        return new Money(playerProfitsSum).toNegative();
    }
}
