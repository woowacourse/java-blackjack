package domain.profit;

import java.util.HashMap;
import java.util.Map;

import domain.participant.Participant;

public class FinalProfitByParticipant {

    private static final double INIT_FINAL_PROFIT_VALUE = 0.0;

    private final Map<Participant, FinalProfit> finalProfitByParticipant;

    public FinalProfitByParticipant() {
        this.finalProfitByParticipant = new HashMap<>();
    }

    public void putParticipantFinalProfit(Participant participant, FinalProfit finalProfit) {
        finalProfitByParticipant.put(participant, finalProfit);
    }

    public Double calculateDealerFinalProfit() {
        return finalProfitByParticipant.values()
                .stream()
                .reduce(new FinalProfit(INIT_FINAL_PROFIT_VALUE), FinalProfit::add)
                .convertToNegative()
                .getProfit();
    }

    public Map<String, Double> getFinalProfitByParticipantForPrint() {
        Map<String, Double> finalProfitByParticipantForPrint = new HashMap<>();
        for (Participant participant : finalProfitByParticipant.keySet()) {
            FinalProfit participantFinalProfit = finalProfitByParticipant.get(participant);
            finalProfitByParticipantForPrint.put(participant.getName(), participantFinalProfit.getProfit());
        }
        return Map.copyOf(finalProfitByParticipantForPrint);
    }
}
