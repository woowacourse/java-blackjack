package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Hit extends Running {
    public Hit(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Cards cards) {
        this.cards.combine(cards);
        if (this.cards.isBust()) {
            return new Bust(this.cards);
        }
        return new Hit(this.cards);
    }

    @Override
    public State stay() {
        return new Stay(this.cards);
    }
}
