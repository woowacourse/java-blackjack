package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int CAN_HIT_LIMIT = 17;

    public Card getFirstCard() {
        return getCards().get(0);
    }

    @Override
    public boolean canHit() {
        return hand.getSum() < CAN_HIT_LIMIT;
    }
}
