package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stand extends Finish {

    Stand(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate(final BlackjackGameState blackjackGameState) {
        return 0;
    }
}
