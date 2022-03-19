package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class BlackJack extends Finished {


    BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State state) {
        if (state instanceof BlackJack) {
            return 1;
        }
        return 1.5;
    }
}
