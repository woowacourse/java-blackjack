package blackjack.domain.result;

import blackjack.domain.gamer.GameParticipant;
import blackjack.domain.gamer.GameParticipants;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameStatistics {

    private final Map<GameParticipant, List<GameResult>> participantToResults;

    private GameStatistics(Map<GameParticipant, List<GameResult>> participantToResults) {
        this.participantToResults = participantToResults;
    }

    public static GameStatistics initialize(GameParticipants participants) {
        Map<GameParticipant, List<GameResult>> participantToResults = new LinkedHashMap<>();

        participants.getGameParticipants().forEach(participant ->
                participantToResults.put(participant, new LinkedList<>())
        );

        return new GameStatistics(participantToResults);
    }

    public void markResult(GameParticipant hero, GameParticipant villain, GameResult result) {
        participantToResults.get(hero).add(result);
        participantToResults.get(villain).add(result.oppose());
    }

    public List<GameResult> find(GameParticipant participant) {
        return participantToResults.get(participant);
    }

    public Map<GameParticipant, List<GameResult>> getParticipantToResults() {
        return Collections.unmodifiableMap(participantToResults);
    }
}
