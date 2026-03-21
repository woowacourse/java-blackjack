package domain.rule;

import domain.card.Card;
import domain.card.Cards;

public class Hit extends Running {
    public Hit(Cards cards) {
        super(cards);
    }

    public static State of(Cards cards) {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State draw(Card card) {
        cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
