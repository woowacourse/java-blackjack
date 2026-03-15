package domain.participant;

public class Dealer extends Participant {
    private static final int DEALER_HIT_CRITERION = 17;

    @Override
    public boolean checkScoreUnderCriterion() {
        return cardBoard.calculateScore() < DEALER_HIT_CRITERION;
    }
}
