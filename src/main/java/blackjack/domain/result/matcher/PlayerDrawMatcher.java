package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerDrawMatcher implements PlayerResultMatcher {
    @Override
    public boolean isResultMatched(Player player, Dealer dealer) {
        return !dealer.isBust() && areSameScore(player, dealer) && areSameNaturalBlackjack(player, dealer);
    }

    private static boolean areSameScore(Player player, Dealer dealer) {
        return player.getScore() == dealer.getScore();
    }

    private boolean areSameNaturalBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() == dealer.isBlackjack();
    }
}
