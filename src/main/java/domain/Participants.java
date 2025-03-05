package domain;

import java.util.List;

public class Participants {
    private static final int MAX_SIZE = 5;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validateSize(participants);
        this.participants = participants;
    }

    private void validateSize(List<Participant> participants) {
        if (participants.isEmpty() || participants.size() > MAX_SIZE) {
            throw new IllegalArgumentException("참여자 수는 최소 1인 이상 최대 5인 이하여야 합니다.");
        }
    }
}
