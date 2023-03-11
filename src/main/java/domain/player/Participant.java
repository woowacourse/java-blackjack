package domain.player;

import domain.card.Score;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
    
    @Override
    public GameResult battleResult(Player dealer) {
        if (isBust() || dealer.isBust()) {
            return decideGameResultWithBust();
        }
        
        Score totalScore = getTotalScore();
        Score totalScoreOfOtherPlayer = dealer.getTotalScore();
        return decideGameResultWithScore(totalScore, totalScoreOfOtherPlayer);
    }
    
    private GameResult decideGameResultWithBust() {
        if (isBust()) {
            return GameResult.LOSE;
        }
        
        return GameResult.WIN;
    }
}
