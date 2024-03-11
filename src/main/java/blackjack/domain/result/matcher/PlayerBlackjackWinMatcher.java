package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerBlackjackWinMatcher implements PlayerResultMatcher {
    @Override
    public MatchResult match(Player player, Dealer dealer) {
        if (player.isNaturalBlackjack() && !dealer.isNaturalBlackjack()) {
            return MatchResult.MATCH;
        }
        return MatchResult.NOT_MATCH;
    }
}
