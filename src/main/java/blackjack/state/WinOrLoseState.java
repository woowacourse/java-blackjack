package blackjack.state;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.state.finalState.LoseState;
import blackjack.state.finalState.WinState;

public class WinOrLoseState implements State {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        if (player.sumCards() > dealer.sumCards()) {
            player.changeState(new WinState());
            player.calculateState(dealer);
            return;
        }
        player.changeState(new LoseState());
        player.calculateState(dealer);
    }
}
