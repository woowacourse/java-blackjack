package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class PlayerResultMatcher {

    public static BlackJackResult match(Dealer dealer, Player player) {
        if (!player.isBusted()) {
            return player.calculate().match(dealer.calculate());
        }
        return BlackJackResult.LOSE;
    }
}
