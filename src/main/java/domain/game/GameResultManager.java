package domain.game;

import domain.betting.CalculateProfit;
import domain.betting.Revenue;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.math.BigDecimal;
import java.util.Map;
import java.util.LinkedHashMap;

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
        return players.judgeResultsAgainst(dealer);
    }

    public Map<Name, Revenue> getParticipantsProfit() {
        Map<Name, Revenue> playerRevenues = players.calculateProfitsAgainst(dealer, calculateProfit);
        Map<Name, Revenue> finalRevenues = new LinkedHashMap<>();
        finalRevenues.put(dealer.getName(), calculateDealerRevenue(playerRevenues));
        finalRevenues.putAll(playerRevenues);
        return finalRevenues;
    }

    private Revenue calculateDealerRevenue(Map<Name, Revenue> revenues) {
        BigDecimal totalPlayerRevenue = revenues.values().stream()
                .map(Revenue::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Revenue(totalPlayerRevenue.negate());
    }
}
