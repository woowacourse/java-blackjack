package blackjack.domain.participant;

import blackjack.domain.GameResult;

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
    
    public void stopDrawing() {
        stopDrawing = true;
    }
    
    public GameResult calculateResult(Dealer dealer) {
        if (isBusted()) {
            return GameResult.LOSE;
        }
        if (dealer.isBusted()) {
            return GameResult.WIN;
        }
        return compareScore(getScore(), dealer.getScore());
    }
    
    private GameResult compareScore(int dealerScore, int playerScore) {
        if (playerScore == dealerScore) {
            return GameResult.DRAW;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }
}
