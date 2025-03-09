package domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public void add(Participant participant) {
        participants.add(participant);
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
}
