package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerWinMatcher implements PlayerResultMatcher {
    @Override
    public boolean isResultMatched(Player player, Dealer dealer) {
        return dealer.isBust() || !player.isBlackjack() && isPlayerWinning(player, dealer);
    }

    private boolean isPlayerWinning(Player player, Dealer dealer) {
        return !player.isBust() && player.getScore() > dealer.getScore();
    }
}
