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

    public void update(Participant participant) {
        int score = participant.calculateScore();
        participantStatuses.put(participant, new GameStatus(getStatusByScore(score), score));
    }

    public GameStatus getGameStatusByParticipant(Participant participant) {
        return participantStatuses.get(participant);
    }

    public ParticipantStatus getStatusByScore(int score) {
        if (score > 21) {
            return ParticipantStatus.BUST;
        }
        if (score == 21) {
            return ParticipantStatus.BLACK_JACK;
        }
        return ParticipantStatus.NOT_BUST;
    }
}
