package domain.state;

import domain.card.Card;
import domain.card.Cards;

public class Hittable extends Running {

    public Hittable(Cards cards) {
        super(cards);
    }

    public static Hittable initialState() {
        return new Hittable(Cards.emptyCards());
    }

    @Override
    public State hit(Card card) {
        Cards cards = cards();
        cards.addCard(card);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        } else if (cards.isBust()) {
            return new Bust(cards);
        } else if (cards.isBustThreshold()) {
            return new Stay(cards);
        }
        return new Hittable(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards());
    }
}
