package domain.participant;

import java.util.LinkedHashMap;

public class BetResult {

    LinkedHashMap<Participant, BetAmount> betAmountByParticipant;
    public BetResult(LinkedHashMap<Participant, BetAmount> result) {
        betAmountByParticipant = result;
    }


    public BetAmount findByParticipant(Participant participant) {
        return betAmountByParticipant.get(participant);
    }
}
