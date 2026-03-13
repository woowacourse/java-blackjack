package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    public static int DEALER_HIT_STAND_BOUNDARY = 16;

    public Dealer(String name) {
        super(name);
    }

    public Card getFirstCard() {
        return cards.peek();
    }

    public boolean decideHitStand() {
        return cards.getTotalSum() <= DEALER_HIT_STAND_BOUNDARY;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
