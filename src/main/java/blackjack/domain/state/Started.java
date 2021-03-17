package blackjack.domain.state;

import blackjack.domain.user.Cards;

public abstract class Started implements State {
    protected final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final Cards cards() {
        return cards;
    }
}
