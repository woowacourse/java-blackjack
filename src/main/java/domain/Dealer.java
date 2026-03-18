package domain;

public class Dealer extends Participant {
    public static final int DEALER_HIT_LIMIT = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return !state.isFinished() &&  state.getScore() < DEALER_HIT_LIMIT;
    }

    public String getFirstCardName(){
        return state.getFirstCard().getCardName();
    }
}
