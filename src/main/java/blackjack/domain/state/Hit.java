package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public class Hit extends Running {
    public Hit(List<Card> cards) {
        this(new Cards(cards));
    }

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.addCard(card);
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
    public boolean isFinished() {
        return false;
    }
}
