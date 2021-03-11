package blackjack.domain.player.state;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.Result;

public class NonBlackJackState implements State {
    @Override
    public void blackJackCheck(final Dealer dealer, final Challenger challenger) {
        if (challenger.isBlackJack()) {
            challenger.changeState(new BlackJackState());
        }
    }

    @Override
    public void compareCards(final Dealer dealer, final Challenger challenger) {
        if (dealer.isBust() || dealer.getScore() < challenger.getScore()) {
            challenger.changeState(new WinState());
            return;
        }
        if (dealer.getScore() == challenger.getScore()) {
            challenger.changeState(new DrawsState());
        }
        if (dealer.getScore() > challenger.getScore()) {
            challenger.changeState(new LoseState());
        }
    }

    @Override
    public Result getResult() {
        throw new UnsupportedOperationException();
    }
}
