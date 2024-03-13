package domain.participant;

import domain.blackjack.WinStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class BetResult {

    LinkedHashMap<Participant, BetAmount> betAmountByParticipant;
    public BetResult(LinkedHashMap<Participant, BetAmount> result) {
        betAmountByParticipant = result;
    }


    public BetAmount findByParticipant(Participant participant) {
        return betAmountByParticipant.get(participant);
    }

    public void updateToProfit(LinkedHashMap<Participant, WinStatus> winStatusByParticipant) {
        for (Map.Entry<Participant, WinStatus> winStatusEntry : winStatusByParticipant.entrySet()) {
            Participant participant = winStatusEntry.getKey();
            WinStatus winStatus = winStatusEntry.getValue();
            BetAmount betAmount = betAmountByParticipant.get(participant);

            if (WinStatus.WIN == winStatusEntry.getValue()) {
                BetAmount profit = betAmount.multiply(winStatus.getProfitMultiplier());
                betAmountByParticipant.replace(participant, profit);
            }
        }
    }
}
