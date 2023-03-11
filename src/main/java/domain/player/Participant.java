package domain.player;

import domain.card.Score;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }
    
    @Override
    public ParticipantGameResult battleResult(Player dealer) {
        if (isBust() || dealer.isBust()) {
            return decideGameResultWithBust();
        }
        
        Score totalScore = getTotalScore();
        Score totalScoreOfOtherPlayer = dealer.getTotalScore();
        return decideGameResultWithScore(totalScore, totalScoreOfOtherPlayer);
    }
    
    private ParticipantGameResult decideGameResultWithBust() {
        if (isBust()) {
            return ParticipantGameResult.LOSE;
        }
        
        return ParticipantGameResult.WIN;
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        return getState().calculateProfit(betAmount);
    }
}
