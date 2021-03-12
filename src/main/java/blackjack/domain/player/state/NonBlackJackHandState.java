package blackjack.domain.player.state;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.Result;

public class NonBlackJackHandState implements HandState {
    @Override
    public void blackJackCheck(final Dealer dealer, final Challenger challenger) {
        if (challenger.isBlackJack()) {
            challenger.changeState(new BlackJackHandState());
        }
    }

    @Override
    public void compareCards(final Dealer dealer, final Challenger challenger) {
        if (dealer.isBust() || dealer.getScore() < challenger.getScore()) {
            challenger.changeState(new WinHandState());
            return;
        }
        if (dealer.getScore() == challenger.getScore()) {
            challenger.changeState(new DrawsHandState());
        }
        if (dealer.getScore() > challenger.getScore()) {
            challenger.changeState(new LoseHandState());
        }
    }

    @Override
    public Result getResult() {
        throw new UnsupportedOperationException();
    }
}
