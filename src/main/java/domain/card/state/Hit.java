package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public class Hit extends Started {
    protected Hit(Cards cards) {
        super(cards);
    }

    @Override
    public CardState receive(Card card) {
        cards().addCard(card);
        if (cards().isBust()) {
            return new Bust(cards());
        }
        return this;
    }

    @Override
    public CardState finish() {
        return new Stay(cards());
    }
}
