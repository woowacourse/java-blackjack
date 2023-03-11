package domain.player;

import domain.card.Score;

public class Dealer extends Player {
    public Dealer() {
        super("딜러");
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
    
    @Override
    public GameResult battleResult(Player participant) {
        if (isBust() || participant.isBust()) {
            return decideGameResultWithBust();
        }
        
        Score totalScore = getTotalScore();
        Score totalScoreOfOtherPlayer = participant.getTotalScore();
        return decideGameResultWithScore(totalScore, totalScoreOfOtherPlayer);
    }
    
    private GameResult decideGameResultWithBust() {
        if (isBust()) {
            return GameResult.WIN;
        }
        
        return GameResult.LOSE;
    }
}
