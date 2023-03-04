package domain.game;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;

import domain.participant.Result;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<Participant, Result> gameResults;

    public GameResult(final Map<Participant, Result> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResult create(final Participants participants) {
        Map<Participant, Result> gameResults = makeGameResults(participants);

        return new GameResult(gameResults);
    }

    private static Map<Participant, Result> makeGameResults(final Participants participants) {
        final Dealer dealer = (Dealer) participants.getDealer();
        final List<Participant> players = participants.getPlayer();

        return players.stream().collect(Collectors.toMap(Function.identity(), dealer::calculateResult,
                (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }

    public Map<String, Result> getPlayerGameResults() {
        return gameResults.keySet().stream()
                .collect(Collectors.toMap(Participant::getName, gameResults::get,
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }
}
