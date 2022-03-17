package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.state.BlackjackGameState;

public final class Bust extends Finished {

    private static final double BUST_LOSE_RATE = -1;

    public Bust(final Cards cards, final Score score) {
        super(cards, score);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        return BUST_LOSE_RATE;
    }
}
