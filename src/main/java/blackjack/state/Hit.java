package blackjack.state;

import blackjack.domain.card.Card;

public class Hit extends Running {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State hit(Card card) {
        cards.add(card);

        if (cards.isBust()) {
            return new Bust(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public Score score() {
        return this.cards.score();
    }
}
