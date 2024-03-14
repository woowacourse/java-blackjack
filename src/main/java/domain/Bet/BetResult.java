package domain.Bet;

import domain.blackjack.WinStatus;
import domain.participant.Participant;

import java.util.LinkedHashMap;
import java.util.Map;

public class BetResult {

    private final LinkedHashMap<Participant, BetAmount> betAmountByParticipant;

    public BetResult(final LinkedHashMap<Participant, BetAmount> result) {
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

            BetAmount profit = betAmount.multiply(winStatus.getProfitMultiplier());
            betAmountByParticipant.replace(participant, profit);
        }
    }

    public double calculateDealerProfit() {
        double participantTotalProfit = betAmountByParticipant.values()
                .stream()
                .mapToDouble(BetAmount::getValue)
                .sum();
        return -participantTotalProfit;
    }

    public LinkedHashMap<Participant, BetAmount> getBetAmountByParticipant() {
        return betAmountByParticipant;
    }
}
