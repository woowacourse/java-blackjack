package blackjack.state;

import static blackjack.blackjack.Blackjack.BLACKJACK_CARD_COUNT;
import static blackjack.blackjack.Blackjack.BLACKJACK_SCORE;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;

public class BlackjackState implements State, FinalState {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        if (dealer.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            player.changeState(new PushState());
            player.calculateState(dealer);
        }
        calculateEarnedMoney(player);
    }

    @Override
    public void calculateEarnedMoney(final Player player) {
        player.blackjackGame();
    }
}
