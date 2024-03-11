package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerDrawMatcher implements PlayerResultMatcher {
    @Override
    public MatchResult match(Player player, Dealer dealer) {
        if (areSameNaturalBlackjack(player, dealer) && player.getScore() == dealer.getScore()) {
            return MatchResult.MATCH;
        }
        return MatchResult.NOT_MATCH;
    }

    private boolean areSameNaturalBlackjack(Player player, Dealer dealer) {
        return player.isNaturalBlackjack() == dealer.isNaturalBlackjack();
    }
}