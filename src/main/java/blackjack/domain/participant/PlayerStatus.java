package blackjack.domain.participant;

public enum PlayerStatus {

    HIT(true),
    BUST(false),
    STAY(false),
    BLACKJACK(false),
    ;

    private final boolean isRunning;

    PlayerStatus(final boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
