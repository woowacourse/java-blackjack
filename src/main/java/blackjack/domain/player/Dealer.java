package blackjack.domain.player;

public class Dealer extends User {

    private static final int MINIMUM_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean hasUnderMinimumScore() {
        return hand.getTotalScore() < MINIMUM_SCORE;
    }
}
