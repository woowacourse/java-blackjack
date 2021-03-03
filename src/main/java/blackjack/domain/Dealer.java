package blackjack.domain;

public class Dealer extends Participant {

    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int currentScore = getFinalScore();
        return currentScore <= MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }
}
