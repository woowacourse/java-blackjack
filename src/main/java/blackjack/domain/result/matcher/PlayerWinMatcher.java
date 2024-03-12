package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerWinMatcher implements PlayerResultMatcher {
    @Override
    public MatchResult match(Player player, Dealer dealer) {
        if (dealer.isBust() || !player.isBlackjack() && isPlayerWinning(player, dealer)) {
            return MatchResult.MATCH;
        }
        return MatchResult.NOT_MATCH;
    }

    private boolean isPlayerWinning(Player player, Dealer dealer) {
        return !player.isBust() && player.getScore() > dealer.getScore();
    }
}
