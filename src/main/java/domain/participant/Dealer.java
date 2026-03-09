package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    public Dealer(String name) {
        super(name);
    }

    public Card getFirstCard() {
        return cards.peek();
    }

    public boolean decideHitStand(int boundary) {
        return cards.getTotalSum() <= boundary;
    }
}
