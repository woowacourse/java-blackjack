package domain.user;

import domain.Hand;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int UPPER_LIMIT_TO_DRAW = 16;

    public Dealer() {
        super.userName = new DealerName(DEALER_NAME);
        super.hand = new Hand();
    }

    public Dealer(Hand hand) {
        super.userName = new DealerName(DEALER_NAME);
        super.hand = hand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() <= UPPER_LIMIT_TO_DRAW;
    }
}
