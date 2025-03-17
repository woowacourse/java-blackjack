package blackjack.state;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;

public interface State {

    void checkState(final Player player, final Dealer dealer);
}
