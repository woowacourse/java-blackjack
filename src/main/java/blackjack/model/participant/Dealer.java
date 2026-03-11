package blackjack.model.participant;

import blackjack.model.hand.Hand;

public class Dealer extends Participant {

    private static final int DRAW_UPPER_BOUND = 16;

    public Dealer() {
        super();
    }

    public Dealer(Hand hand) {
        super(hand);
    }

    public boolean shouldDraw() {
        return hand.calculateScore() <= DRAW_UPPER_BOUND;
    }
}
