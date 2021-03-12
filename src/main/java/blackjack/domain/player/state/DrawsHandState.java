package blackjack.domain.player.state;

import blackjack.domain.player.Challenger;
import blackjack.domain.player.Dealer;
import blackjack.domain.result.Result;

public class DrawsHandState implements HandState {
    @Override
    public void blackJackCheck(Dealer dealer, Challenger challenger) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void compareCards(Dealer dealer, Challenger challenger) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Result getResult() {
        return Result.DRAW;
    }
}
