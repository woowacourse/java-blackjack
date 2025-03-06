package blackjack.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validate(participants);
        this.participants = new ArrayList<>(participants);
    }

    public void validate(List<Participant> paritipants) {
        validateParticipantCount(paritipants);
        validateDuplicateName(paritipants);
    }

    private void validateParticipantCount(List<Participant> paritipants) {
        if (paritipants.size() < 2 || paritipants.size() > 8) {
            throw new IllegalArgumentException("참여자는 2~8명 이여야 합니다.");
        }
    }

    private void validateDuplicateName(List<Participant> participants) {
        Set<Participant> uniqueParticipants = new HashSet<>(participants);
        System.out.println(uniqueParticipants.size() + " " + participants.size());
        if (uniqueParticipants.size() != participants.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }
}
