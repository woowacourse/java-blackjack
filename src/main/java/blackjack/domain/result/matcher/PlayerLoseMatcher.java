package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerLoseMatcher implements PlayerResultMatcher {
    @Override
    public MatchResult match(Player player, Dealer dealer) {
        boolean isDealerBust = dealer.isBust();
        if (player.isBust() && !isDealerBust) {
            return MatchResult.MATCH;
        }
        if ((!isDealerBust && player.getScore() < dealer.getScore()) || (dealer.isBlackjack() && !player.isBlackjack())) {
            return MatchResult.MATCH;
        }
        return MatchResult.NOT_MATCH;
    }
}
