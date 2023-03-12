package blackjack.domain.user;

public class Dealer extends User {

    public static final int MINIMUM_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean hasUnderMinimumScore() {
        return handCards.getTotalScore() <= MINIMUM_SCORE;
    }
}
