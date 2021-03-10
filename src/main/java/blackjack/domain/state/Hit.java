package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

    private Cards cards;

    public Hit(final Cards cards) {
        this.cards = cards;
    }

    public State draw(Card card) {
        cards = cards.addCard(card);
        if (cards.isBust()) {
            return new Bust();
        }
        return new Hit(cards);
    }

    public State stay() {
        return new Stay();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double money) {
        throw new IllegalStateException();
    }
}
