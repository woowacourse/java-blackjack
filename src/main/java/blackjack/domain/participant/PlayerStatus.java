package blackjack.domain.participant;

public enum PlayerStatus {

    HIT(false, false),
    BUST(true, true),
    STAY(true, false),
    ;

    private final boolean isFinish;
    private final boolean isBust;

    PlayerStatus(final boolean isFinish, final boolean isBust) {
        this.isFinish = isFinish;
        this.isBust = isBust;
    }

    public boolean isRunning() {
        return !isFinish;
    }
}
