package blackjack.state;

import blackjack.gamer.Player;

public interface FinalState {

    void calculateEarnedMoney(final Player player);
}
