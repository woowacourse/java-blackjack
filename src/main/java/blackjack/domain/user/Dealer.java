package blackjack.domain.user;

public class Dealer extends User {

    private static final UserName DEALER_NAME = new UserName("딜러");
    private static final int SEVEN_TEEN = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isValidRange() {
        return getScore() < SEVEN_TEEN;
    }
}
