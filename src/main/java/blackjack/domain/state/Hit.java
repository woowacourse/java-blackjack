package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Hit implements State {
    private final Cards cards;

    public Hit(List<Card> cards) {
        this(new Cards(cards));
    }

    public Hit(Cards cards) {
        this.cards = cards;
    }

    public State draw(Card card) {
        cards.addCard(card);

        if (cards.isBust()) {
            return new Bust();
        }
        if (cards.isStay()) {
            return new Stay();
        }
        return new Hit(cards);
    }

    public State stay() {
        return new Stay();
    }
}
