package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Running extends Started {

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final Status draw(Card card) {
        final Cards cards = getCards();
        cards.receiveCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public final Status stay() {
        return new Stay(getCards());
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final boolean isRunning() {
        return true;
    }
}
