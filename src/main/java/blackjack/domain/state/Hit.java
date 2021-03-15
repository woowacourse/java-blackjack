package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements State {
    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State addCard(Card card) {
        cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public double earningsRate() {
        throw new UnsupportedOperationException();
    }
}
