package domain;

import java.util.Collections;
import java.util.List;

public class Participants {

    private static final int PLAYER_MAX_SIZE = 8;
    private static final int DEALER = 1;

    private List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateParticipantSize(participants);
        this.participants = participants;
    }

    private void validateParticipantSize(List<Participant> participants) {
        if (participants.size() > PLAYER_MAX_SIZE + DEALER) {
            throw new IllegalArgumentException("게임의 최대 참여자는 %d명을 넘을 수 없습니다.".formatted(
                    PLAYER_MAX_SIZE));
        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<Participant> getPlayerParticipants() {
        return participants.stream()
                .filter(participant -> participant.getClass() == Player.class)
                .toList();
    }
}
