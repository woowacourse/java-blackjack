package blackjack.domain.result.matcher;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

@FunctionalInterface
public interface PlayerResultMatcher {
    boolean isResultMatched(Player player, Dealer dealer);
}
