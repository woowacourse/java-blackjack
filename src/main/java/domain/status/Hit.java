package domain.status;

import domain.BlackJackInfo;
import domain.participant.HandCards;
import domain.card.Card;

public final class Hit extends Running {
    public Hit(final HandCards cards) {
        super(cards);
    }

    @Override
    public Status draw(final Card card) {
        cards.receiveHitCard(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        if (cards.calculateScore() == BlackJackInfo.BLACKJACK_SCORE) {
            return new Stay(cards);
        }
        return new Hit(cards);
    }

    @Override
    public Status stay() {
        return new Stay(cards);
    }
}
