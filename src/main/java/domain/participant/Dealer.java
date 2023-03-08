package domain.participant;

public class Dealer extends Participant {
    private static final int LIMIT_TAKE_CARD_VALUE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean dealerMustDraw() {
        int score = calculateScore();
        return score > 0 && score < LIMIT_TAKE_CARD_VALUE;
    }
}
