package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public final class Bust extends Finish {

    private static final double BUST_LOSE_RATE = -1;

    Bust(final Cards cards, final Score score) {
        super(cards, score);
    }

    @Override
    final double earningRate(final BlackjackGameState blackjackGameState) {
        return BUST_LOSE_RATE;
    }
}
