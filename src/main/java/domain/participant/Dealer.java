package domain.participant;

import domain.card.Card;

import static util.BlackJackConstant.DEALER_HIT_LIMIT;

public class Dealer extends Participant {

    public boolean shouldHit() {
        return hand.calculateSum() <= DEALER_HIT_LIMIT;
    }

    public Card getOpenCard() {
        return hand.getHand().getFirst();
    }
}
