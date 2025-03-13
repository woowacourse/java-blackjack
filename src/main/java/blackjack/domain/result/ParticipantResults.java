package blackjack.domain.result;

import blackjack.domain.Participant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantResults {
    List<ParticipantResult> participantResults;

    public ParticipantResults() {
        this.participantResults = new ArrayList<>();
    }

    public void add(ParticipantResult newParticipantResult) {
        Optional<ParticipantResult> existedResult = findSameResult(newParticipantResult);

        if (existedResult.isPresent()) {
            existedResult.get().update(newParticipantResult);
            return;
        }
        participantResults.add(newParticipantResult);
    }

    private Optional<ParticipantResult> findSameResult(ParticipantResult participantResult) {
        return participantResults.stream()
                .filter(result -> result.equals(participantResult))
                .findAny();
    }

    public ParticipantResult findResult(Participant participant) {
        return participantResults.stream()
                .filter(result -> result.isResultOf(participant))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 참여자에 대한 기록이 존재하지 않습니다."));
    }
}
