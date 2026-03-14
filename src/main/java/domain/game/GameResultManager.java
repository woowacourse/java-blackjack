package domain.game;

import domain.betting.CalculateProfit;
import domain.betting.Revenue;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.util.HashMap;
import java.util.Map;

public class GameResultManager {
    private final CalculateProfit calculateProfit;

    public GameResultManager(CalculateProfit calculateProfit) {
        this.calculateProfit = calculateProfit;
    }

    public Map<Name, Revenue> getParticipantsProfit(Players players, Dealer dealer) {
        Map<Name, Revenue> finalRevenues = new HashMap<>();
        players.forEach(player -> {
            GameResult result = player.judgeResult(dealer);
            Revenue revenue = calculateProfit.calculate(player.getName(), result);
            finalRevenues.put(player.getName(), revenue);
        });
        return finalRevenues;
    }
}
