package blackjack.domain.participant;

public class Player extends Participant {
    
    private boolean stopDrawing;
    
    public Player(String nickname) {
        super(nickname);
        validate(nickname);
        stopDrawing = false;
    }
    
    private static void validate(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("플레이어 이름은 공백이 될 수 없습니다.");
        }
    }
    
    public void stop() {
        stopDrawing = true;
    }
    
    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        return hand.calculateTotalScore(BUSTED_SCORE) < BUSTED_SCORE;
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
}
