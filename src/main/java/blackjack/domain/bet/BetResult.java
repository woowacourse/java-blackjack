package blackjack.domain.bet;

import blackjack.domain.participant.ParticipantName;
import java.util.Map;

public class BetResult {

    private final Map<ParticipantName, BetRevenue> playersBetRevenue;

    public BetResult(final Map<ParticipantName, BetRevenue> playersBetRevenue) {
        this.playersBetRevenue = playersBetRevenue;
    }

    public BetRevenue calculateDealerRevenue() {
        final double sum = playersBetRevenue.values().stream()
                .mapToDouble(BetRevenue::value)
                .sum();

        return new BetRevenue(-sum);
    }
}
