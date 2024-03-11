package domain.cards.gamercards;

import domain.cards.Card;

public class DealerCards extends PlayerCards {

    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int FIRST_CARD_INDEX = 0;

    public DealerCards() {
        super();
    }

    public boolean hasScoreUnderHitThreshold() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }
}
