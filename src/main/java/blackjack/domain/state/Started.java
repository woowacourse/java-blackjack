package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Started implements State {
    protected Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
