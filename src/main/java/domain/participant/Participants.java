package domain.participant;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public Participant findDealer() {
        return participants.stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 딜러가 존재하지 않는 상태입니다."));
    }

    public List<Participant> findPlayers() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .toList();
    }

    public Map<Participant, Integer> getTotalRankSumByPlayer() {
        return participants.stream()
                .filter(participant -> !participant.isDealer())
                .collect(Collectors.toMap(participant -> participant,
                        Participant::getTotalRankSum));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Participants that = (Participants) object;
        return Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(participants);
    }
}
