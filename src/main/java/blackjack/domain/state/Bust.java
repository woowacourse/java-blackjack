package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public final class Bust extends Finish {

    Bust(final Cards cards, final Score score) {
        super(cards, score);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        return -1;
    }
}
