package blackjack.state;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;

public class PushState implements State, FinalState {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        calculateEarnedMoney(player);
    }

    @Override
    public void calculateEarnedMoney(final Player player) {
        player.pushGame();
    }
}
