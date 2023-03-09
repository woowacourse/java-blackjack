package domain.player;

public final class Dealer extends Participant {

    private static final int STOP_LOWER_BOUND = 17;

    public Dealer() {
        super(new Hand());
    }

    public boolean isDealerHit() {
        return getScore() < STOP_LOWER_BOUND;
    }
}
