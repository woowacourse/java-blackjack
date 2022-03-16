package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finish {

    Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(final BlackjackGameState blackjackGameState) {
        return -1;
    }
}
