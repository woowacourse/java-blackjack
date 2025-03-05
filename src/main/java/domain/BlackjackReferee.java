package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackReferee {

    public Map<Participant,GameResultStatus> judge(Participant dealer, List<Participant> participants) {
        Map<Participant, GameResultStatus> result = new HashMap<>();
        int dealerSum = dealer.cards().calculateSumResult();
        for (Participant participant : participants) {
            int playerSum = participant.cards().calculateSumResult();
            if(playerSum >= 22) {
                result.put(participant, GameResultStatus.LOSE);
            }
            if(playerSum > dealerSum) {
                result.put(participant, GameResultStatus.WIN);
            }
            if(playerSum == dealerSum) {
                result.put(participant, GameResultStatus.DRAW);
            }
            if(playerSum < dealerSum) {
                result.put(participant, GameResultStatus.LOSE);
            }
        }
        return result;
    }
}
