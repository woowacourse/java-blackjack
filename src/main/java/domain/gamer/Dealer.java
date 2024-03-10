package domain.gamer;

public class Dealer extends Gamer {
    private static final String DEALER_NAME = "딜러";
    private static final int STAY_CONDITION = 17;

    public Dealer() {
        super(new Name(DEALER_NAME));
    }

    @Override
    public boolean isStay() {
        return calculateTotalScore() >= STAY_CONDITION;
    }
}
