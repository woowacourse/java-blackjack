package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResultStatistics {

    private final Participant participant;
    private final Map<ResultType, ResultCount> stats = new EnumMap<>(ResultType.class);

    private ResultStatistics(final Participant participant) {
        this.participant = participant;
        Arrays.stream(ResultType.values())
                .forEach(this::initializeResult);
    }

    public static ResultStatistics of(final Participant participant) {
        return new ResultStatistics(participant);
    }

    private void initializeResult(final ResultType resultType) {
        stats.put(resultType, new ResultCount());
    }

    public void incrementCountOf(final ResultType resultType) {
        ResultCount count = stats.get(resultType);
        count.increment();
    }

    public Participant getParticipant() {
        return participant;
    }

    public Map<ResultType, ResultCount> getStats() {
        return stats;
    }

    @Override
    public String toString() {
        return "ResultStatistics{" +
                "participant=" + participant +
                ", stats=" + stats +
                '}';
    }
}
