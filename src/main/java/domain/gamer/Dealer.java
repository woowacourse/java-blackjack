package domain.gamer;

public class Dealer extends Gamer {

    private static final int DEALER_HIT_CONDITION = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return this.getTotalScore() <= DEALER_HIT_CONDITION && !this.isBust();
    }
}
