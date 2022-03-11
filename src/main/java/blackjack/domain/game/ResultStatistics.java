package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import blackjack.dto.ParticipantCardsDto;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class ResultStatistics {

    private final ParticipantCardsDto participantCardsDto;
    private final Map<ResultType, ResultCount> stats = new EnumMap<>(ResultType.class);

    private ResultStatistics(Participant participant) {
        this.participantCardsDto = ParticipantCardsDto.of(participant);
        Arrays.stream(ResultType.values())
                .forEach(this::initializeResult);
    }

    public static ResultStatistics of(Participant participant) {
        return new ResultStatistics(participant);
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
        return participantCardsDto.getName();
    }

    public Set<Card> getCards() {
        return participantCardsDto.getCards();
    }

    @Override
    public String toString() {
        return "ResultStatistics{" +
                "participantCardsDto=" + participantCardsDto +
                ", stats=" + stats +
                '}';
    }
}
