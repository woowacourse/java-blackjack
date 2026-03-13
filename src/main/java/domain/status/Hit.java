package domain.status;

import domain.participant.HandCards;
import domain.card.Card;

import java.util.List;

public final class Hit extends Running {
    public Hit(final HandCards cards) {
        super(cards);
    }

    @Override
    public Status drawInitialCards(List<Card> cards) {
        return null;
    }

    @Override
    public Status draw(final Card card) {
        cards.receiveHitCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public Status stay() {
        return new Stay(cards);
    }
}
