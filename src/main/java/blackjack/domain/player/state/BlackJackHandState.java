package blackjack.domain.player.state;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.Result;

public class BlackJackHandState implements HandState {
    @Override
    public void blackJackCheck(final Dealer dealer, final Challenger challenger) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void compareCards(final Dealer dealer, final Challenger challenger) {
        if (dealer.isBlackJack()) {
            challenger.changeState(new DrawsHandState());
            return;
        }
        challenger.changeState(new BlackJackWinHandState());
    }

    @Override
    public Result getResult() {
        throw new UnsupportedOperationException();
    }
}
