package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Name;
import blackjack.domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bettings {

    private final Map<Player, Money> bettings;

    public Bettings(final Map<Player, Money> bettings) {
        this.bettings = bettings;
    }

    public Map<Name, Money> calculatePlayerProfits(final Dealer dealer) {
        final Map<Name, Money> playerProfits = new LinkedHashMap<>();
        bettings.forEach((player, betting) -> {
            final PlayerOutcome playerOutcome = Judge.calculatePlayerOutcome(dealer, player);
            playerProfits.put(player.getName(), betting.multiply(playerOutcome.getProfitMultiplier()));
        });

        return playerProfits;
    }

    public Money calculateDealerProfit(final Dealer dealer) {
        final double playerProfitsSum = calculatePlayerProfits(dealer).values()
                .stream()
                .mapToDouble(Money::value)
                .sum();
        return new Money(playerProfitsSum).toNegative();
    }
}
