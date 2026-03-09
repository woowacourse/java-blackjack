package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.dto.PlayerGameResult;

public class Player extends Participant {
    
    private boolean stopDrawing;
    
    public Player(String nickname) {
        super(nickname);
        stopDrawing = false;
    }
    
    @Override
    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        if (isBusted()) {
            return false;
        }
        return !hand.isBlackjack();
    }
    
    public void stop() {
        stopDrawing = true;
    }
    
    public PlayerGameResult determinePlayerResult(Dealer dealer) {
        GameResult gameResult = determineGameResult(dealer);
        return new PlayerGameResult(nickname, gameResult);
    }
    
    private GameResult determineGameResult(Dealer dealer) {
        if (isBusted()) {
            return GameResult.LOSE;
        }
        if (dealer.isBusted()) {
            return GameResult.WIN;
        }
        return compareScore(dealer.getTotalScore(), getTotalScore());
    }
    
    private GameResult compareScore(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return GameResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }
}
