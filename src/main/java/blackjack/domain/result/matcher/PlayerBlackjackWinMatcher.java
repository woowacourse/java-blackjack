package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerBlackjackWinMatcher implements PlayerResultMatcher {
    @Override
    public boolean isResultMatched(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }
}
