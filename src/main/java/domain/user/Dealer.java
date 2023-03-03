package domain.user;

import domain.CardHand;

public class Dealer extends AbstractUser{
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Dealer(CardHand cardHand) {
        super(DEALER_NAME, cardHand);
    }

    @Override
    public boolean canAdd() {
        return false;
    }
}
