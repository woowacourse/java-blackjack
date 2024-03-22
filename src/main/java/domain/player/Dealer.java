package domain.player;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_THRESHOLD = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return HIT_THRESHOLD > hand.calculateScore();
    }
}
