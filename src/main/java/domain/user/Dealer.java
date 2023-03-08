package domain.user;

import domain.Hand;

public final class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int UPPER_LIMIT_TO_DRAW = 16;

    public Dealer() {
        this(new DealerName(DEALER_NAME), new Hand());
    }

    public Dealer(Hand hand) {
        this(new DealerName(DEALER_NAME), hand);
    }

    public Dealer(DealerName dealerName, Hand hand) {
        super.userName = dealerName;
        super.hand = hand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() <= UPPER_LIMIT_TO_DRAW;
    }
}
