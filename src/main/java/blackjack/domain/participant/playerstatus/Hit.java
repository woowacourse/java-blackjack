package blackjack.domain.participant.playerstatus;

public final class Hit implements PlayerStatus {

    private static final Hit INSTANCE = new Hit();

    private Hit() {
    }

    public static Hit getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
