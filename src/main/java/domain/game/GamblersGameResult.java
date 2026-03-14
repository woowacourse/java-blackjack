package domain.game;

import static util.Constants.REVERSE_SIGN;

import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.Participant;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GamblersGameResult {

    private Map<String, Profit> participantProfits;

    public GamblersGameResult(Participant dealer, Gamblers gamblers) {
        this.participantProfits = new HashMap<>();
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


    public Profit getDealerProfit() {
        int totalProfit = participantProfits.values().stream()
                .mapToInt(Profit::getProfit)
                .sum();
        return new Profit(totalProfit * REVERSE_SIGN);
    }
}
