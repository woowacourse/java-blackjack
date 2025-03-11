package domain;

public class Dealer extends Gamer {

    private static final String NAME = "딜러";
    private static final int HIT_THRESHOLD = 16;

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean canHit() {
        return getHand().calculateTotalPoint() <= HIT_THRESHOLD && !isBurst();
    }

}
