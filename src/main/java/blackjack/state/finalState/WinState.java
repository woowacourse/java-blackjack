package blackjack.state.finalState;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.state.State;

public class WinState implements State, FinalState {
    @Override
    public void checkState(final Player player, final Dealer dealer) {
        calculateEarnedMoney(player);
    }

    @Override
    public void calculateEarnedMoney(final Player player) {
        player.winGame();
    }
}
