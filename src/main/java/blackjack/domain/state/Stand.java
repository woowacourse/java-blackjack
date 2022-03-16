package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stand extends AbstractBlackjackGameState {

    Stand(final Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        return null;
    }

    @Override
    public BlackjackGameState stay() {
        return null;
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
