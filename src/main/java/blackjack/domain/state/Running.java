package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Running implements State {

    private final Cards cards;

    Running(final Cards cards) {
        this.cards = cards;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double profit(double money, State state) {
        throw new IllegalStateException("현재 Running 상태");
    }
}
