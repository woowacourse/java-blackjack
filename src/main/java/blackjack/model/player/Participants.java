package blackjack.model.player;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Participants {
    public static final int MINIMUM_PARTICIPANTS_COUNT = 2;
    public static final int MAXIMUM_PARTICIPANTS_COUNT = 8;

    private final List<Participant> participants;

    public Participants(final List<Participant> participants) {
        validate(participants);
        this.participants = new ArrayList<>(participants);
    }

    public void validate(final List<Participant> participants) {
        validateParticipantCount(participants);
        validateDuplicateName(participants);
    }

    private void validateParticipantCount(final List<Participant> participants) {
        if (participants.size() < MINIMUM_PARTICIPANTS_COUNT || participants.size() > MAXIMUM_PARTICIPANTS_COUNT) {
            throw new IllegalArgumentException(String.format("참가자는 %d~%d명 이여야 합니다.", MINIMUM_PARTICIPANTS_COUNT, MAXIMUM_PARTICIPANTS_COUNT));
        }
    }

    private void validateDuplicateName(final List<Participant> participants) {
        Set<Participant> uniqueParticipants = new HashSet<>(participants);
        if (uniqueParticipants.size() != participants.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public List<String> getAllNames() {
        return participants.stream()
                .map(Participant::getName)
                .toList();
    }
}
