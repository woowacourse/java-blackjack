package domain.game;

import domain.card.Score;
import domain.player.ParticipantGameResult;
import domain.player.Player;

import java.util.*;

public class Referee {
    private final Map<ParticipantGameResult, Integer> dealerGameResults;
    private final Map<Player, ParticipantGameResult> participantsGameResults;
    
    public Referee() {
        dealerGameResults = new EnumMap<>(ParticipantGameResult.class);
        participantsGameResults = new HashMap<>();
    }
    
    public void decidePlayersBattleResults(BlackJackGame blackJackGame) {
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
    
        savePlayersBattleResults(dealer, participants);
    }
    
    private void savePlayersBattleResults(Player dealer, List<Player> participants) {
        for (Player participant : participants) {
            ParticipantGameResult dealerParticipantGameResult = dealer.battleResult(participant);
            dealerGameResults.put(dealerParticipantGameResult, dealerGameResults.getOrDefault(dealerParticipantGameResult, 0) + 1);
            
            ParticipantGameResult participantGameResult = participant.battleResult(dealer);
            participantsGameResults.put(participant, participantGameResult);
        }
    }
    
    public Map<Player, ParticipantGameResult> participantsGameResults() {
        return Collections.unmodifiableMap(participantsGameResults);
    }
    
    public Map<ParticipantGameResult, Integer> dealerGameResults() {
        return Collections.unmodifiableMap(dealerGameResults);
    }
    
    public int decidePlayersBattleResults1(Player dealer, Player participant, int betAmount) {
        return 0;
    }
    
    private ParticipantGameResult decideGameResultWithScore(Score participantTotalScore, Score DealerTotalScore) {
        if (participantTotalScore.isOverThen(DealerTotalScore)) {
            return ParticipantGameResult.WIN;
        }
        
        if (participantTotalScore.isLessThen(DealerTotalScore)) {
            return ParticipantGameResult.LOSE;
        }
        
        return ParticipantGameResult.DRAW;
    }
}
