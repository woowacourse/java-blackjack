package domain.game;

import domain.card.Score;
import domain.player.ParticipantGameResult;
import domain.player.Player;

import java.util.*;

public class Referee {
    private final Map<Player, Double> betAmountResult;
    private final Map<ParticipantGameResult, Integer> dealerGameResults;
    private final Map<Player, ParticipantGameResult> participantsGameResults;
    
    public Referee() {
        dealerGameResults = new EnumMap<>(ParticipantGameResult.class);
        participantsGameResults = new HashMap<>();
        betAmountResult = new HashMap<>();
    }
    
    // TODO : 삭제
    public void decidePlayersBattleResults(BlackJackGame blackJackGame) {
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
    
        savePlayersBattleResults(dealer, participants);
    }
    
    // TODO : 삭제
    private void savePlayersBattleResults(Player dealer, List<Player> participants) {
        for (Player participant : participants) {
            ParticipantGameResult dealerParticipantGameResult = dealer.battleResult(participant);
            dealerGameResults.put(dealerParticipantGameResult, dealerGameResults.getOrDefault(dealerParticipantGameResult, 0) + 1);
            
            ParticipantGameResult participantGameResult = participant.battleResult(dealer);
            participantsGameResults.put(participant, participantGameResult);
        }
    }
    
    // TODO : 삭제
    public Map<Player, ParticipantGameResult> participantsGameResults() {
        return Collections.unmodifiableMap(participantsGameResults);
    }
    
    // TODO : 삭제
    public Map<ParticipantGameResult, Integer> dealerGameResults() {
        return Collections.unmodifiableMap(dealerGameResults);
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
