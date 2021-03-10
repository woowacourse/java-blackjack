package blackjack.domain.user;

public class Dealer extends User {
    private static final int DEALER_REDRAW_STANDARD = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canContinue() {
        return this.getScore() < DEALER_REDRAW_STANDARD;
    }
}
