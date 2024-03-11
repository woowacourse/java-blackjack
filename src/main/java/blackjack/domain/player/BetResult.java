package blackjack.domain.player;

import blackjack.domain.player.bet.BetRevenue;
import java.util.Map;

public class BetResult {

    private final Map<PlayerName, BetRevenue> playersBetRevenue;

    public BetResult(final Map<PlayerName, BetRevenue> playersBetRevenue) {
        this.playersBetRevenue = playersBetRevenue;
    }

    public BetRevenue calculateDealerRevenue() {
        final double sum = playersBetRevenue.values().stream()
                .mapToDouble(BetRevenue::value)
                .sum();

        return new BetRevenue(-sum);
    }
}
