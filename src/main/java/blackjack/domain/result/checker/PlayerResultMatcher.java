package blackjack.domain.result.checker;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

@FunctionalInterface
public interface PlayerResultMatcher {
    MatchResult match(Player player, Dealer dealer);
}
