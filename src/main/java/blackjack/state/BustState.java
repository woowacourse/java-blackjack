package blackjack.state;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.state.finalState.LoseState;

public class BustState implements State {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        player.changeState(new LoseState());
        player.calculateState(dealer);
    }
}
