package domain.game;

import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GamblersGameResult {

    private final Map<String, Profit> participantProfits;

    private GamblersGameResult(Map<String, Profit> participantProfits) {
        this.participantProfits = participantProfits;
    }

    public static GamblersGameResult calculate(Participant dealer, Gamblers gamblers) {
        Map<String, Profit> calculatedProfits = new HashMap<>();
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult result = GameResult.determine(dealer, gambler);
            Profit profit = Profit.calculateProfit(result, gambler.getBettingAmount());
            calculatedProfits.put(gambler.getName(), profit);
        }
        return new GamblersGameResult(calculatedProfits);
    }

    public Profit getMatchProfits(String name) {
        return participantProfits.get(name);
    }

    public Map<String, Profit> getParticipantProfits() {
        return participantProfits;
    }

    public Profit getDealerProfit() {
        Profit totalGamblerProfit = new Profit(BigDecimal.ZERO);
        for (Profit profit : participantProfits.values()) {
            totalGamblerProfit = totalGamblerProfit.add(profit);
        }
        return totalGamblerProfit.negate();
    }
}
