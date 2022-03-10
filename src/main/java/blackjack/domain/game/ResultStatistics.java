package blackjack.domain.game;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class ResultStatistics {

    private final String participantName;
    private final Map<ResultType, ResultCount> stats = new EnumMap<>(ResultType.class);

    private ResultStatistics(String participantName) {
        this.participantName = participantName;
        Arrays.stream(ResultType.values())
                .forEach(this::initializeResult);
    }

    public static ResultStatistics of(String participantName) {
        return new ResultStatistics(participantName);
    }

    private void initializeResult(ResultType resultType) {
        stats.put(resultType, new ResultCount());
    }

    public ResultCount getCountOf(ResultType resultType) {
        return stats.get(resultType);
    }

    public void incrementCountOf(ResultType resultType) {
        ResultCount count = stats.get(resultType);
        count.increment();
    }

    public String getParticipantName() {
        return participantName;
    }

    @Override
    public String toString() {
        return "ResultStatistics{" + "stats=" + stats + '}';
    }
}
