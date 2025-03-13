package domain.participant;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParticipantNames {

    private static final int MAXIMUM_NAMES_SIZE = 4;
    private final List<ParticipantName> participantNames;

    public ParticipantNames(List<ParticipantName> participantNames) {
        validateRange(participantNames);
        validateDuplicate(participantNames);
        this.participantNames = participantNames;
    }

    private void validateRange(List<ParticipantName> participantNames) {
        if (participantNames.size() > MAXIMUM_NAMES_SIZE) {
            throw new IllegalArgumentException("[ERROR] 이름은 최대 4개까지 입력 가능합니다.");
        }
    }

    private void validateDuplicate(List<ParticipantName> participantNames) {
        Set<ParticipantName> removeDuplicatedNames = new HashSet<>(participantNames);
        if (removeDuplicatedNames.size() != participantNames.size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }

    public List<ParticipantName> getParticipantNames() {
        return Collections.unmodifiableList(participantNames);
    }
}
