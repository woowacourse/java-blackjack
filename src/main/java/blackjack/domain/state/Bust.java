package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Bust extends Finish {

    Bust(final Cards cards, final int score) {
        super(cards, score);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        return -1;
    }
}
