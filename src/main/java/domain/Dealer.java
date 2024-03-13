package domain;

public class Dealer extends Gamer {
    private static final int DEALER_HIT_CONDITION = 16;

    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public boolean canHit() {
        return this.getTotalScore() <= DEALER_HIT_CONDITION && !this.isBust();
    }
}
