package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.HandGenerator;

import java.util.List;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int INITIAL_OPENED_CARD_COUNT = 1;
    private static final int HIT_THRESHOLD = 16;

    public Dealer(HandGenerator handGenerator) {
        super(new Name(DEALER_NAME), handGenerator);
    }

    @Override
    public List<Card> getInitialOpenedCards() {
        return getCardsByCount(INITIAL_OPENED_CARD_COUNT);
    }

    @Override
    public boolean canHit() {
        return !isBlackjack() && !isTotalScoreGreaterThan(HIT_THRESHOLD);
    }
}
