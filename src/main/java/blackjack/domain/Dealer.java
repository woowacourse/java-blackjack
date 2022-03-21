package blackjack.domain;

public class Dealer extends Participant {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_MIN_TOTAL = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card showFirstCard() {
        return super.getHoldingCard().pickFirstCard();
    }

    public boolean isFinished() {
        return super.getHoldingCard().calculateTotal() >= DEALER_MIN_TOTAL;
    }

}
