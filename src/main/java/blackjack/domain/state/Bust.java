package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finish {

    Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public int score() {
        if (cards().isBust()) {
            return cards().score();
        }
        return cards().maxScore();
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        return -1;
    }
}
