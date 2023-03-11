package domain.user;

import domain.card.Hand;

public final class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int UPPER_LIMIT_TO_DRAW = 16;
    private static final int NO_BETTING_AMOUNT = 0;

    public Dealer() {
        this(UserInformation.from(new DealerName(DEALER_NAME), NO_BETTING_AMOUNT), new Hand());
    }

    public Dealer(Hand hand) {
        this(UserInformation.from(new DealerName(DEALER_NAME), NO_BETTING_AMOUNT), hand);
    }

    public Dealer(UserInformation userInformation, Hand hand) {
        super.userInformation = userInformation;
        super.hand = hand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() <= UPPER_LIMIT_TO_DRAW;
    }
}
