package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public class DealerHit extends Hit {

    private static final int MAX_SCORE = 16;

    DealerHit(PlayerCards cards) {
        this.cards = cards;
    }
    
    public static State from(PlayerCards cards) {
        if (cards.isBust()) {
            return new DealerBust(cards);
        }
        if (cards.getTotalScore() > MAX_SCORE) {
            return new Stay(cards);
        }
        return new DealerHit(cards);
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new DealerBust(cards);
        }
        if (cards.getTotalScore() > MAX_SCORE) {
            return new Stay(cards);
        }
        return this;
    }
}
