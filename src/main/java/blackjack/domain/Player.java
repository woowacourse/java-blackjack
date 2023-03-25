package blackjack.domain;

public class Player extends Person {
    private static final int PLAYER_STOP_HIT_BOUND = 21;

    private final Name name;
    private final BettingAmount bettingAmount;

    public Player(Name name, BettingAmount bettingAmount) {
        super();
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    @Override
    public boolean isHitPossible() {
        int score = getScore();
        return score < PLAYER_STOP_HIT_BOUND;
    }

    public String getName() {
        return name.getName();
    }

    public int getBettingAmountToInt() {
        return bettingAmount.getBettingAmount();
    }
}
