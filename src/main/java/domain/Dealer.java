package domain;

public class Dealer extends Participants {
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public Boolean dealerRule() {
        return getTotalCardScore() < 17;
    }
}
