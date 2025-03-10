package blackjack.model.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Participants {
    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validate(participants);
        this.participants = new ArrayList<>(participants);
    }

    public void validate(List<Participant> participants) {
        validateParticipantCount(participants);
        validateDuplicateName(participants);
    }

    public Stream<Participant> stream() {
        return participants.stream();
    }

    public void forEach(Consumer<Participant> consumer) {
        participants.forEach(consumer);
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    private void validateParticipantCount(List<Participant> participants) {
        if (participants.size() < 2 || participants.size() > 8) {
            throw new IllegalArgumentException("참여자는 2~8명 이여야 합니다.");
        }
    }

    private void validateDuplicateName(List<Participant> participants) {
        Set<Participant> uniqueParticipants = new HashSet<>(participants);
        if (uniqueParticipants.size() != participants.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }
}
