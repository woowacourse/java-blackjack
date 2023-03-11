package domain.game;

import domain.card.Score;
import domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Referee {
    private final Map<Player, Double> betAmountResult;
    
    public Referee() {
        betAmountResult = new HashMap<>();
    }
    
    public void saveParticipantBetAmount(Player participant, double betAmount) {
        betAmountResult.put(participant, betAmount);
    }
    
    public void saveBattleResults(BlackJackGame blackJackGame) {
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
        
        for (Player participant : participants) {
            double participantProfit = decideParticipantProfit(dealer, participant, betAmountResult.get(participant));
            betAmountResult.put(participant, participantProfit);
            betAmountResult.put(dealer, getOrZero(dealer) - participantProfit);
        }
    }
    
    private double decideParticipantProfit(Player dealer, Player participant, double betAmount) {
        Score participantTotalScore = participant.getTotalScore();
        Score dealerTotalScore = dealer.getTotalScore();
        double profit = participant.calculateProfit(betAmount);
        
        if (participant.isBust() || dealer.isBust()) {
            return profit;
        }
        
        return decideParticipantProfitWithScore(participantTotalScore, dealerTotalScore, profit);
    }
    
    private double decideParticipantProfitWithScore(Score participantTotalScore, Score dealerTotalScore, double profit) {
        if (participantTotalScore.isOverThen(dealerTotalScore)) {
            return profit;
        }
        
        if (participantTotalScore.isLessThen(dealerTotalScore)) {
            return -profit;
        }
        
        return 0;
    }
    
    private double getOrZero(Player player) {
        return betAmountResult.getOrDefault(player, 0.0);
    }
    
    public double getPlayerBetAmount(Player player) {
        return betAmountResult.get(player);
    }
}
