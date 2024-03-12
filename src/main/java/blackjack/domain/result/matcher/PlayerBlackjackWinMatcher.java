package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerBlackjackWinMatcher implements PlayerResultMatcher {
    @Override
    public MatchResult match(Player player, Dealer dealer) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return MatchResult.MATCH;
        }
        return MatchResult.NOT_MATCH;
    }
}
