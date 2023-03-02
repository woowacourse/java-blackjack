package domain.participant;

public class Dealer extends Participant {

    private static final int STAY_LOWER_BOUND = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean isStay() {
        return calculateScore() > STAY_LOWER_BOUND;
    }
}
