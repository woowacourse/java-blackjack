package blackjack.domain.participant;

public class Player extends Participant {
    
    private static final int BUSTED_SCORE = 21;

    private boolean stopDrawing;

    public Player(String nickname) {
        super(nickname, Role.PLAYER);
        stopDrawing = false;
    }

    public void stop() {
        stopDrawing = true;
    }

    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        return hand.calculateTotalScore() < BUSTED_SCORE;
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
}
