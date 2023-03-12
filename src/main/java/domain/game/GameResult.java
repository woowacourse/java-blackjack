package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public final class GameResult {

    private static final List<Participant> BLANK_LIST = Collections.emptyList();

    private final Map<ResultType, List<Participant>> results;

    private GameResult(final Map<ResultType, List<Participant>> results) {
        this.results = results;
    }

    public static GameResult of(final Dealer dealer, List<Participant> participants) {
        final Map<ResultType, List<Participant>> result = participants.stream()
                .collect(groupingBy(participant -> ResultType.of(dealer, participant)));

        return new GameResult(result);
    }

    public List<Participant> getWinners() {
        return results.getOrDefault((ResultType.VICTORY), BLANK_LIST);
    }

    public List<Participant> getLosers() {
        return results.getOrDefault(ResultType.DEFEAT, BLANK_LIST);
    }

    public List<Participant> getDrawParticipants() {
        return results.getOrDefault(ResultType.TIE, BLANK_LIST);
    }
}
