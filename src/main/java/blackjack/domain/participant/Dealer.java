package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT = 16;

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return isHit() && getState().hand().hardSum() <= DEALER_HIT;
    }
}
