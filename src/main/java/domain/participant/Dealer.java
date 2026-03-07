package domain.participant;

import static domain.Constant.DEALER_HIT_STAND_BOUNDARY;

import domain.card.Card;

public class Dealer extends Participant {
    public Dealer(String name) {
        super(name);
    }

    public boolean shouldGetMoreCard(){
        int result = cards.getTotalSum();
        return result <= DEALER_HIT_STAND_BOUNDARY;
    }

    public Card getFirstCard(){
        return cards.peek();
    }

    @Override
    public String toString() {
        return cards.toString();
    }

}
