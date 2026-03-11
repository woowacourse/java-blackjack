package blackjack.domain.participant;

public class Player extends Participant {

    private boolean stopDrawing;

    public Player(String nickname) {
        super(nickname);
        stopDrawing = false;
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
