package domain;

import java.util.Collections;
import java.util.Set;

public class ParticipantsResult {

    private final Set<ParticipantResult> participantsResult;

    public ParticipantsResult(Set<ParticipantResult> participantsResult) {
        this.participantsResult = participantsResult;
    }

    public Set<ParticipantResult> getParticipantsResult() {
        return Collections.unmodifiableSet(participantsResult);
    }
}
