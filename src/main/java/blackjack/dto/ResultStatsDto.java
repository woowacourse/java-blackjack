package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.ResultCount;
import blackjack.domain.game.ResultStatistics;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class ResultStatsDto {

    private static final String INVALID_RESULT_STATS_INITIALIZATION_EXCEPTION_MESSAGE = "승패 통계 정보가 존재하지 않는 데이터입니다.";

    private final ParticipantCardsDto participantCardsDto;
    private final Map<ResultType, ResultCount> participantResultStats = new EnumMap<>(ResultType.class);

    public ResultStatsDto(ResultStatistics resultStats) {
        this.participantCardsDto = ParticipantCardsDto.of(resultStats.getParticipant());
        initializeResultStats(resultStats.getStats());
        validateResultStats();
    }

    private void initializeResultStats(Map<ResultType, ResultCount> stats) {
        stats.keySet()
                .stream()
                .filter(resultType -> stats.get(resultType).toInt() > 0)
                .forEach(resultType -> this.participantResultStats.put(resultType, stats.get(resultType)));
    }

    private void validateResultStats() {
        if (participantResultStats.keySet().isEmpty()) {
            throw new IllegalArgumentException(INVALID_RESULT_STATS_INITIALIZATION_EXCEPTION_MESSAGE);
        }
    }

    public boolean hasSingleResultType() {
        return participantResultStats.keySet().size() == 1;
    }

    public ResultCount getCountOf(ResultType resultType) {
        return participantResultStats.get(resultType);
    }

    public String getParticipantName() {
        return participantCardsDto.getName();
    }

    public Set<Card> getCards() {
        return participantCardsDto.getCards();
    }

    public Score getScore() {
        return participantCardsDto.getScore();
    }

    public Map<ResultType, ResultCount> getResultStats() {
        return Collections.unmodifiableMap(participantResultStats);
    }

    @Override
    public String toString() {
        return "ResultStatsDto{" +
                "participantCardsDto=" + participantCardsDto +
                ", participantResultStats=" + participantResultStats +
                '}';
    }
}
