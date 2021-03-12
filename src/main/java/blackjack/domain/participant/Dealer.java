package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private static final int DEALER_HIT = 16;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return isHit() && getState().hand().hardSum() <= DEALER_HIT;
    }
}
