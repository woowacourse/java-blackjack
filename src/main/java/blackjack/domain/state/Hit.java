package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements Status {

    private final Cards cards;

    Hit(Cards cards) {
        this.cards = new Cards(cards.getCards());
    }

    @Override
    public Status draw(Card card) {
        cards.receiveCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    public Status stay() {
        return new Stay(cards);
    }

    public Cards getCards() {
        return cards;
    }
}
