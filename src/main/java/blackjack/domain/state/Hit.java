package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit extends Running {

    public Hit(final Cards cards) {
        super(cards);
    }

    public State draw(Card card) {
        cards = cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.isStay()) {
            return new Stay(cards);
        }
        return new Hit(cards);
    }

    public State stay() {
        return new Stay(cards);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isHit() {
        return true;
    }
}
