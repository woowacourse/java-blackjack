package blackjack.domain.participant;

public class Player extends Participant {

    private final int bettingAmount;
    private boolean stopDrawing;

    private Player(String nickname, int bettingAmount) {
        super(nickname);
        this.stopDrawing = false;
        this.bettingAmount = bettingAmount;
    }

    public static Player from(String nickname, int bettingAmount) {
        return new Player(nickname, bettingAmount);
    }

    @Override
    public boolean isDrawable() {
        if (stopDrawing || isBusted()) {
            return false;
        }
        return !hand.isBlackjack();
    }

    public void stand() {
        stopDrawing = true;
    }
}
