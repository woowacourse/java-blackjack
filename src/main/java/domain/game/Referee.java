package domain.game;

import domain.player.GameResult;
import domain.player.Player;

import java.util.*;

public class Referee {
    private final Map<GameResult, Integer> dealerGameResults;
    private final Map<Player, GameResult> participantsGameResults;
    
    public Referee() {
        dealerGameResults = new EnumMap<>(GameResult.class);
        participantsGameResults = new HashMap<>();
    }
    
    public void decidePlayersBattleResults(BlackJackGame blackJackGame) {
        Player dealer = blackJackGame.getDealer();
        List<Player> participants = blackJackGame.getParticipants();
    
        savePlayersBattleResults(dealer, participants);
    }
    
    private void savePlayersBattleResults(Player dealer, List<Player> participants) {
        for (Player participant : participants) {
            GameResult dealerGameResult = dealer.battleResult(participant);
            dealerGameResults.put(dealerGameResult, dealerGameResults.getOrDefault(dealerGameResult, 0) + 1);
            
            GameResult participantGameResult = participant.battleResult(dealer);
            participantsGameResults.put(participant, participantGameResult);
        }
    }
    
    public Map<Player, GameResult> participantsGameResults() {
        return Collections.unmodifiableMap(participantsGameResults);
    }
    
    public Map<GameResult, Integer> dealerGameResults() {
        return Collections.unmodifiableMap(dealerGameResults);
    }
}
