package blackjack.domain.participant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {

    private static final int MAXIMUM_PARTICIPANT_COUNT = 6;

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        validate(participants);
        this.participants = List.copyOf(participants);
    }

    private void validate(final List<Participant> participants) {
        validateCount(participants);
        validateDuplicate(participants);
    }

    private void validateCount(final List<Participant> participants) {
        if (participants.size() > MAXIMUM_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException("참가자는 " + MAXIMUM_PARTICIPANT_COUNT + "명을 초과할 수 없습니다");
        }
    }

    private void validateDuplicate(final List<Participant> participants) {
        final Set<Participant> uniqueParticipants = new HashSet<>(participants);

        if (uniqueParticipants.size() != participants.size()) {
            throw new IllegalArgumentException("참가자 이름은 중복될 수 없습니다.");
        }
    }

    public List<Participant> getParticipants() {
        return participants;
    }
}
