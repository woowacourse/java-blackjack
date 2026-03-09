package domain.participant;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public boolean dealerRule() {
        return getTotalCardScore() < 17;
    }
}
