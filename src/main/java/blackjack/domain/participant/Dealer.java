package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int MAX_SCORE_TO_RECEIVE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
    }

    @Override
    public boolean isAbleToReceive() {
        score.calculateScore(extractCardNumbers());
        return score.getScore() <= MAX_SCORE_TO_RECEIVE;
    }

    public String getName() {
        return DEALER_NAME;
    }
}
