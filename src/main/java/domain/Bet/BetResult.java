package domain.Bet;

import domain.blackjack.WinStatus;
import domain.participant.Participant;

import java.util.Map;

public class BetResult {

    private final Map<Participant, BetAmount> betAmountByParticipant;

    public BetResult(final Map<Participant, BetAmount> result) {
        betAmountByParticipant = result;
    }

    public BetAmount findByParticipant(Participant participant) {
        return betAmountByParticipant.get(participant);
    }

    public void updateToProfit(Map<Participant, WinStatus> winStatusByParticipant) {
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

    public Map<Participant, BetAmount> getBetAmountByParticipant() {
        return betAmountByParticipant;
    }
}
