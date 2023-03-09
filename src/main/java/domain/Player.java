package domain;

public class Player extends Participant {

    private static final int UPPER_BOUND_OF_DRAWABLE_SCORE = 21;

    private final BettingMoney bettingMoney;
    private Decision decision;

    public Player(Name name, int bettingMoney) {
        super(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
        this.decision = Decision.HIT;
    }

    public void stand() {
        decision = Decision.STAND;
    }

    @Override
    public boolean isDrawable() {
        return decision == Decision.HIT &&
                score() < UPPER_BOUND_OF_DRAWABLE_SCORE;
    }

    public int bettingMoney() {
        return bettingMoney.value();
    }
}
