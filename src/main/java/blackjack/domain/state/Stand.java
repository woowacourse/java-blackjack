package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stand extends AbstractBlackjackGameState {

    public Stand(final Cards cards) {
        super(cards);
    }

    @Override
    public BlackjackGameState hit(final Card card) {
        return null;
    }

    @Override
    public BlackjackGameState stand() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int score() {
        return 0;
    }
}
