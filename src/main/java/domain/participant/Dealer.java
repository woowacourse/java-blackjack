package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_THRESHOLD = 16;


    public Dealer(Hand hand) {
        super(hand);
    }

    public void draw(Card card) {
        if (isUnderThreshold()) {
            addCard(card);
        }
    }

    public boolean isUnderThreshold() {
        return calculateSum() <= DEALER_DRAW_THRESHOLD;
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
    }

    @Override
    public int calculateSum() {
        return super.calculateSum();
    }

    public Hand getHand() {
        return super.getHand();
    }
}
