package domain.profit;

import java.util.HashMap;
import java.util.Map;

import domain.participant.Participant;

public class FinalProfitDto {

    private final Double dealerFinalProfit;
    private final Map<String, Double> finalProfitByPlayer;

    public FinalProfitDto(Double dealerFinalProfit, Map<Participant, FinalProfit> finalProfitByPlayer) {
        this.dealerFinalProfit = dealerFinalProfit;
        this.finalProfitByPlayer = convertForPrint(finalProfitByPlayer);
    }

    private Map<String, Double> convertForPrint(Map<Participant, FinalProfit> finalProfitByPlayer) {
        Map<String, Double> finalProfitByParticipantForPrint = new HashMap<>();
        for (Participant participant : finalProfitByPlayer.keySet()) {
            FinalProfit participantFinalProfit = finalProfitByPlayer.get(participant);
            finalProfitByParticipantForPrint.put(participant.getName(), participantFinalProfit.getProfit());
        }
        return Map.copyOf(finalProfitByParticipantForPrint);
    }

    public Double getDealerFinalProfit() {
        return dealerFinalProfit;
    }

    public Map<String, Double> getFinalProfitByPlayer() {
        return Map.copyOf(finalProfitByPlayer);
    }
}
