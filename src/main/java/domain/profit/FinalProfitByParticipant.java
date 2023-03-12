package domain.profit;

import java.util.LinkedHashMap;
import java.util.Map;

import domain.participant.Participant;

public class FinalProfitByParticipant {

    private static final double INIT_FINAL_PROFIT_VALUE = 0.0;

    private final Map<Participant, FinalProfit> finalProfitByParticipant;

    public FinalProfitByParticipant() {
        this.finalProfitByParticipant = new LinkedHashMap<>();
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

    public Map<Participant, FinalProfit> getFinalProfitByParticipant() {
        return Map.copyOf(finalProfitByParticipant);
    }
}
