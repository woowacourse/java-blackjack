package blackjack.state;

import static blackjack.blackjack.Blackjack.BLACKJACK_SCORE;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;

public class NotBlackState implements State {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        if (player.isBust(BLACKJACK_SCORE)) {
            player.changeState(new BustState());
            player.calculateState(dealer);
            return;
        }
        player.changeState(new NotBustState());
        player.calculateState(dealer);
    }
}
