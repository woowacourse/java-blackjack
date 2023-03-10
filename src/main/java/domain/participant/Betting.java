package domain.participant;

import java.util.HashMap;
import java.util.Map;

public class Betting {
    private final Map<Participant, Money> bettingResult;

    public Betting() {
        this.bettingResult = new HashMap<>();
    }

    public void setParticipantBet(Participant participant, Money money) {
        bettingResult.put(participant, money);
    }

    public Money getParticipantBet(Participant participant) {
        return bettingResult.get(participant);
    }
}
