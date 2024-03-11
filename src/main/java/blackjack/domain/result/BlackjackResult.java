package blackjack.domain.result;

import java.util.List;
import java.util.Collections;

public class BlackjackResult {
    private final List<ParticipantProfit> participantProfits;

    public BlackjackResult(List<ParticipantProfit> participantProfits) {
        this.participantProfits = Collections.unmodifiableList(participantProfits);
    }

    public List<ParticipantProfit> getParticipantProfits() {
        return participantProfits;
    }
}
