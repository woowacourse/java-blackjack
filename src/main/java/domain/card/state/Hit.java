package domain.card.state;

import domain.card.Card;
import domain.card.Cards;

public class Hit implements CardState {
    private final Cards cards;

    public Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public CardState receive(Card card) {
        cards.addCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return this;
    }

    @Override
    public CardState finish() {
        return new Stay(cards);
    }
}
