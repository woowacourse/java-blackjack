package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerDrawMatcher implements PlayerResultMatcher {
    @Override
    public MatchResult match(Player player, Dealer dealer) {
        if (!dealer.isBust() && areSameScore(player, dealer) && areSameNaturalBlackjack(player, dealer)) {
            return MatchResult.MATCH;
        }
        return MatchResult.NOT_MATCH;
    }

    private static boolean areSameScore(Player player, Dealer dealer) {
        return player.getScore() == dealer.getScore();
    }

    private boolean areSameNaturalBlackjack(Player player, Dealer dealer) {
        return player.isNaturalBlackjack() == dealer.isNaturalBlackjack();
    }
}
