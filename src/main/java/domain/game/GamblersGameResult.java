package domain.game;

import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.util.LinkedHashMap;
import java.util.Map;

public class GamblersGameResult {

    public static final int REVERSE_SIGN = -1;

    private Map<String, Profit> participantProfits;

    public GamblersGameResult(Participant dealer, Gamblers gamblers) {
        this.participantProfits = new LinkedHashMap<>();
        calculateProfits(dealer, gamblers);
    }

    private void calculateProfits(Participant dealer, Gamblers gamblers) {
        for (Gambler gambler : gamblers.getGamblers()) {
            GameResult result = GameResult.determine(dealer, gambler);
            Profit profit = result.calculateProfit(gambler.getBettingAmount());
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
        int totalProfit = participantProfits.values().stream()
                .mapToInt(Profit::getProfit)
                .sum();
        return new Profit(totalProfit * REVERSE_SIGN);
    }
}
