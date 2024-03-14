package blackjack.domain.player;

import blackjack.domain.bet.BetRevenue;
import java.util.Map;

public final class PlayerBetResult {

    private final Map<PlayerName, BetRevenue> playersBetRevenue;

    public PlayerBetResult(final Map<PlayerName, BetRevenue> playersBetRevenue) {
        this.playersBetRevenue = playersBetRevenue;
    }

    public BetRevenue calculateDealerRevenue() {
        final BetRevenue sum = playersBetRevenue.values().stream()
                .reduce(BetRevenue::plus)
                .orElse(new BetRevenue(0F));

        return sum.negate();
    }
}
