package blackjack.domain.result;

import blackjack.domain.game.Participant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ParticipantResult {
    private final Map<GameResultType, Integer> countsOfResultTypes;
    private final Participant participant;
    private final int value;

    public ParticipantResult(Participant participant, GameResultType gameResultType, int value) {
        this.countsOfResultTypes = new LinkedHashMap<>();
        countsOfResultTypes.put(gameResultType, 1);
        this.participant = participant;
        this.value = value;
    }

    public void update(ParticipantResult newParticipantResult) {
        newParticipantResult.getCountsOfResultTypes()
                .forEach(this::addCountOf);
    }

    private void addCountOf(GameResultType gameResultType, int count) {
        countsOfResultTypes.merge(gameResultType, count, Integer::sum);
    }

    public boolean isResultOf(Participant participant) {
        return participant.equals(this.participant);
    }

    public boolean isResultOfChallenger() {
        return participant.isChallenger();
    }

    public Participant getParticipant() {
        return participant;
    }

    public int getValue() {
        return value;
    }

    public Map<GameResultType, Integer> getCountsOfResultTypes() {
        return countsOfResultTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ParticipantResult that)) {
            return false;
        }
        return Objects.equals(participant, that.participant);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(participant);
    }
}
