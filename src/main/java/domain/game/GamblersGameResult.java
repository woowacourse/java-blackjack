package domain.game;

import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class GamblersGameResult {

    private Map<String, Profit> participantProfits;

    public GamblersGameResult(Participant dealer, Gamblers gamblers) {
        this.participantProfits = new LinkedHashMap<>();
        calculateProfits(dealer, gamblers);
    }

    private void calculateProfits(Participant dealer, Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult result = GameResult.determine(dealer, gambler);
            Profit profit = Profit.calculateProfit(result, gambler.getBettingAmount());
            participantProfits.put(gambler.getName(), profit);
        }
    }

    public Profit getMatchProfits(String name) {
        return participantProfits.get(name);
    }

    public Map<String, Profit> getParticipantProfits() {
        return participantProfits;
    }

    public Profit getDealerProfit() {
        BigDecimal totalProfit = participantProfits.values().stream().map(Profit::getProfit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Profit(totalProfit.negate());
    }
}
