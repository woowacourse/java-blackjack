package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Stay extends Finished {

    public static final int LOSE_RATE = -1;
    public static final int WIN_RATE = 1;
    public static final int DRAW_RATE = 0;

    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State compareState) {
        if (compareState instanceof BlackJack) {
            return LOSE_RATE;
        }
        if (compareState instanceof Bust) {
            return WIN_RATE;
        }
        if (compareState.cards().sum() < this.cards().sum()) {
            return WIN_RATE;
        }
        if (compareState.cards().sum() == this.cards().sum()) {
            return DRAW_RATE;
        }
        return LOSE_RATE;
    }
}
