package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class BlackJack extends Finished {

    public static final int DRAW_RATE = 1;
    public static final double WIN_RATE = 1.5;

    BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State state) {
        if (state instanceof BlackJack) {
            return DRAW_RATE;
        }
        return WIN_RATE;
    }
}
