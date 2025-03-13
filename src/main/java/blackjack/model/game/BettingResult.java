package blackjack.model.game;

import blackjack.model.player.Participant;
import java.util.HashMap;
import java.util.Map;


public class BettingResult {

    private final Map<Participant, Integer> bettingResult;

    public BettingResult(Map<Participant, ParticipantResult> winLoseResult) {
        Map<Participant, Integer> bettingResult = new HashMap<>();
        winLoseResult.forEach((participant, result) -> {
            int betAmount = participant.getBettingMoney();
            int payout = calculatePayout(result, betAmount);
            bettingResult.put(participant, payout);
        });
        this.bettingResult = bettingResult;
    }

    private int calculatePayout(ParticipantResult result, int betAmount) {
        if (result.equals(ParticipantResult.WIN)) {
            return betAmount;
        }
        if (result.equals(ParticipantResult.DRAW)) {
            return 0;
        }
        if (result.equals(ParticipantResult.LOSE)) {
            return -betAmount;
        } else {
            return (int) (betAmount * 1.5);
        }
    }


    public Map<Participant, Integer> getBettingResult() {
        return bettingResult;
    }
}