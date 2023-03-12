package domain.game;

import domain.card.Score;
import domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    private static final int DRAW_PROFIT = 0;
    
    private final Map<Player, Double> profitResults;
    
    public Referee() {
        profitResults = new HashMap<>();
    }
    
    public void saveBattleResults(BlackJackGame blackJackGame) {
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
        
        for (Player participant : participants) {
            double participantBetAmount = blackJackGame.findBetAmountByPlayer(participant);
            double participantProfit = decideParticipantProfit(dealer, participant, participantBetAmount);
            profitResults.put(participant, participantProfit);
            profitResults.put(dealer, getOrZero(dealer) - participantProfit);
        }
    }
    
    private double decideParticipantProfit(Player dealer, Player participant, double betAmount) {
        Score participantTotalScore = participant.getTotalScore();
        Score dealerTotalScore = dealer.getTotalScore();
        double participantProfit = participant.calculateProfit(betAmount);
        
        if (participant.isBust() || dealer.isBust()) {
            return participantProfit;
        }
        
        return decideParticipantProfitWithScore(participantTotalScore, dealerTotalScore, participantProfit);
    }
    
    private double decideParticipantProfitWithScore(Score participantTotalScore, Score dealerTotalScore, double participantProfit) {
        if (participantTotalScore.isOverThen(dealerTotalScore)) {
            return participantProfit;
        }
        
        if (participantTotalScore.isLessThen(dealerTotalScore)) {
            return -participantProfit;
        }
        
        return DRAW_PROFIT;
    }
    
    private double getOrZero(Player player) {
        return profitResults.getOrDefault(player, 0.0);
    }
    
    public double findProfitByPlayer(Player player) {
        return profitResults.get(player);
    }
}
