package blackjack.state;

import static blackjack.blackjack.Blackjack.BLACKJACK_CARD_COUNT;
import static blackjack.blackjack.Blackjack.BLACKJACK_SCORE;

import blackjack.gamer.Dealer;
import blackjack.gamer.Player;
import blackjack.state.finalState.BlackjackState;

public class UndefinedState implements State {

    @Override
    public void checkState(final Player player, final Dealer dealer) {
        if (player.isBlackjack(BLACKJACK_SCORE, BLACKJACK_CARD_COUNT)) {
            player.changeState(new BlackjackState());
            player.calculateState(dealer);
            return;
        }
        player.changeState(new NotBlackState());
        player.calculateState(dealer);
    }
}
