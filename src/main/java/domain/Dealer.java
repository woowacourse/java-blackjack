package domain;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private final Score DEALER_STOP_HIT_STANDARD_SCORE = Score.from(16);

    public Dealer() {
        super(ParticipantName.nameOf(DEALER_NAME));
    }

    @Override
    public boolean isDrawable() {
        Score totalScore = hand.calculateCardSum(DEALER_STOP_HIT_STANDARD_SCORE);
        return totalScore.isLessThanEqual(DEALER_STOP_HIT_STANDARD_SCORE);
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
