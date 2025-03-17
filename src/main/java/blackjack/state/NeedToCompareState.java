package blackjack.state;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.state.finalState.DrawState;

public class NeedToCompareState implements State {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        if (player.sumCards() == dealer.sumCards()) {
            player.changeState(new DrawState());
            player.calculateState(dealer);
            return;
        }
        player.changeState(new WinOrLoseState());
        player.calculateState(dealer);
    }
}
