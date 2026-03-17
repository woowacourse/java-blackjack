package domain.game;

import domain.betting.CalculateProfit;
import domain.betting.Revenue;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Players;
import java.math.BigDecimal;
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
        return players.judgeResultsAgainst(dealer);
    }

    public LinkedHashMap<Name, Revenue> getParticipantsProfit() {
        LinkedHashMap<Name, Revenue> finalRevenues = players.calculateProfitsAgainst(dealer, calculateProfit);
        finalRevenues.put(dealer.getName(), calculateDealerRevenue(finalRevenues));
        return finalRevenues;
    }

    private Revenue calculateDealerRevenue(Map<Name, Revenue> revenues) {
        BigDecimal totalPlayerRevenue = revenues.values().stream()
                .map(Revenue::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Revenue(totalPlayerRevenue.negate());
    }
}
