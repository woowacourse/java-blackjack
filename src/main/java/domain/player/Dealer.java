package domain.player;

import domain.card.Score;

public class Dealer extends Player {
    private static final Player dealer = new Dealer();
    
    private Dealer() {
        super("딜러");
    }
    
    public static Player getInstance() {
        return dealer;
    }
    
    @Override
    public ParticipantGameResult battleResult(Player participant) {
        if (isBust() || participant.isBust()) {
            return decideGameResultWithBust(participant);
        }
        
        Score totalScore = getTotalScore();
        Score totalScoreOfOtherPlayer = participant.getTotalScore();
        return decideGameResultWithScore(totalScore, totalScoreOfOtherPlayer);
    }
    
    private ParticipantGameResult decideGameResultWithBust(Player participant) {
        if (participant.isBust()) {
            return ParticipantGameResult.WIN;
        }
        
        return ParticipantGameResult.LOSE;
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
    
    @Override
    public double calculateProfit(int betAmount) {
        throw new IllegalStateException("딜러는 수익률 계산을 할 수 없습니다.");
    }
}
