package domain.game;

import domain.betting.CalculateProfit;
import domain.betting.Revenue;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultManager {
    private final CalculateProfit calculateProfit;
    private final Players players;
    private final Dealer dealer;

    public GameResultManager(CalculateProfit calculateProfit, Players players, Dealer dealer) {
        this.calculateProfit = calculateProfit;
        this.players = players;
        this.dealer = dealer;
    }

    public Map<String, GameResult> getGameResult() {
        Map<String, GameResult> gameResult = new HashMap<>();
        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            gameResult.put(player.getName().getName(), result);
        });
        return gameResult;
    }

    public LinkedHashMap<Name, Revenue> getParticipantsProfit() {
        LinkedHashMap<Name, Revenue> finalRevenues = new LinkedHashMap<>();
        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            Revenue revenue = calculateProfit.calculate(player.getName(), result);
            finalRevenues.put(player.getName(), revenue);
        });
        finalRevenues.put(dealer.getName(), calculateDealerRevenue(finalRevenues));
        return finalRevenues;
    }

    private Revenue calculateDealerRevenue(Map<Name, Revenue> revenues) {
        int dealerRevenue = -revenues.values().stream()
                .mapToInt(Revenue::getMoney)
                .sum();
        return new Revenue(dealerRevenue);
    }

}
