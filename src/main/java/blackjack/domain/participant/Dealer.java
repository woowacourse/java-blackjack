package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    public static final int DEALER_HIT = 16;

    public Dealer(List<Card> cards) {
        super("딜러", cards);
    }

    @Override
    public boolean isAvailableToTake() {
        return getState().hand().hardSum() <= DEALER_HIT;
    }
}
