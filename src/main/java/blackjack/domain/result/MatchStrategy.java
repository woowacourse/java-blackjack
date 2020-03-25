package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public interface MatchStrategy {

    boolean match(Dealer dealer, Player player);
}