package domain.participant;

public final class Dealer extends Participant {
    private static final int DEALER_STAND_SCORE = 17;

    public Dealer() {
        super();
    }

    public boolean shouldDrawCard() {
        return getScore() < DEALER_STAND_SCORE;
    }
}
