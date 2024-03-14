package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerLoseMatcher implements PlayerResultMatcher {
    @Override
    public boolean isResultMatched(Player player, Dealer dealer) {
        boolean isDealerBust = dealer.isBust();
        if (player.isBust() && !isDealerBust) {
            return true;
        }
        return (!isDealerBust && player.getScore() < dealer.getScore()) || (dealer.isBlackjack() && !player.isBlackjack());
    }
}
