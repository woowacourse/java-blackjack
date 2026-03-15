package domain;

public class Player extends Participant {
    private static final int BUST_THRESHOLD = 21;
    private final int betAmount;

    public Player(String name, int betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    @Override
    public boolean canHit() {
        return getScore() <= BUST_THRESHOLD;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
