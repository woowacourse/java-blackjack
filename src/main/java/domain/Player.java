package domain;

public class Player extends Participant {
    private static final int BUST_THRESHOLD = 21;
    private final BetAmount betAmount; // int 대신 객체 사용

    public Player(PlayerName name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    @Override
    public boolean canHit() {
        return getScore() <= BUST_THRESHOLD;
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }
}
