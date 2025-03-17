package blackjack.state;

import static blackjack.blackjack.Blackjack.BLACKJACK_SCORE;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;

public class NotBustState implements State {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        if (dealer.isBust(BLACKJACK_SCORE)) {
            player.changeState(new WinOrLoseState());
            player.calculateState(dealer);
            return;
        }
        player.changeState(new NeedToCompareState());
        player.calculateState(dealer);
    }
}
