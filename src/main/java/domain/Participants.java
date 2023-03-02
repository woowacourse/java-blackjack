package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Map<Participant, GameStatus> participantStatuses = new LinkedHashMap<>();

    public Participants(List<Participant> participants) {
        participants.forEach(
            (participant) -> participantStatuses.put(participant, new GameStatus(ParticipantStatus.NOT_BUST, 0)));
    }
}
