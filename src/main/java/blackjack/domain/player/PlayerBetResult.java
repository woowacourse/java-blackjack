package blackjack.domain.player;

import blackjack.domain.bet.BetRevenue;
import java.util.Map;

public final class PlayerBetResult {

    private final Map<PlayerName, BetRevenue> playersBetRevenue;

    public PlayerBetResult(final Map<PlayerName, BetRevenue> playersBetRevenue) {
        this.playersBetRevenue = playersBetRevenue;
    }

    public BetRevenue calculateDealerRevenue() {
        final double sum = playersBetRevenue.values().stream()
                .mapToDouble(BetRevenue::value)
                .sum();

        return new BetRevenue(-sum);
    }
}
