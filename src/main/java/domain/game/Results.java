package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

public final class Results {

    private static final List<Participant> BLANK_LIST = List.of();

    private final Map<Result, List<Participant>> results;

    private Results(final Map<Result, List<Participant>> results) {
        this.results = results;
    }

    public static Results of(final Dealer dealer, List<Participant> participants) {
        final Map<Result, List<Participant>> result = participants.stream()
                .collect(groupingBy(participant -> Result.of(dealer, participant)));

        return new Results(result);
    }

    public int countWinners() {
        return getWinners().size();
    }

    public int countLosers() {
        return getLosers().size();
    }

    public int countDrawParticipants() {
        return getDrawParticipants().size();
    }

    public List<Participant> getWinners() {
        return results.getOrDefault((Result.VICTORY), BLANK_LIST);
    }

    public List<Participant> getLosers() {
        return results.getOrDefault(Result.DEFEAT, BLANK_LIST);
    }

    public List<Participant> getDrawParticipants() {
        return results.getOrDefault(Result.TIE, BLANK_LIST);
    }
}
