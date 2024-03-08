package domain.cards.gamercards;

import domain.cards.Card;

import java.util.List;

public class DealerCards extends GamerCards {

    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int FIRST_CARD_INDEX = 0;

    public DealerCards(List<Card> cards) {
        super(cards);
    }

    public boolean hasScoreUnderHitThreshold() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }
}
