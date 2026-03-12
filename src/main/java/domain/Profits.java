package domain;

import domain.participant.Participant;
import java.util.Collections;
import java.util.Map;

public class Profits {
    private final Map<Participant, Profit> participantProfits;

    public Profits(Map<Participant, Profit> playerProfits) {
        this.participantProfits = Collections.unmodifiableMap(playerProfits);
    }

    public Map<Participant, Profit> getParticipantProfits() {
        return participantProfits;
    }
}
