package domain.participant;

import domain.card.Card;

import static domain.util.BlackJackConstant.DEALER_HIT_LIMIT;

public class Dealer extends Participant {

    public Card getOpenCard() {
        return hand.getHand().getFirst();
    }

    public boolean shouldHit() {
        return hand.calculateSum() <= DEALER_HIT_LIMIT;
    }
}
