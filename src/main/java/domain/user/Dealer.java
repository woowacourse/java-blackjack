package domain.user;

import domain.CardHand;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int UPPER_LIMIT_TO_DRAW = 16;

    public Dealer() {
        super.userName = new DealerName(DEALER_NAME);
        super.cardHand = new CardHand();
    }

    public Dealer(CardHand cardHand) {
        super.userName = new DealerName(DEALER_NAME);
        super.cardHand = cardHand;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() <= UPPER_LIMIT_TO_DRAW;
    }
}
