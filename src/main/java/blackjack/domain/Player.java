package blackjack.domain;

public class Player extends Person {
    private static final int PLAYER_STOP_HIT_BOUND = 21;
    private static final int ZERO = 0;

    private final Name name;
    private BettingAmount bettingAmount;

    private Player(Name name) {
        super();
        this.name = name;
        this.bettingAmount = new BettingAmount(ZERO);
    }

    public static Player from(String name) {
        return new Player(new Name(name));
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

    public void setBettingAmount(BettingAmount bettingAmount) {
        this.bettingAmount = bettingAmount;
    }
}
