package domain.player;


public class Dealer extends Player {

    private static final int DEALER_STOP_SCORE = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    public boolean canStand() {
        return score() >= DEALER_STOP_SCORE;
    }

}
