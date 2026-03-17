package team.blackjack.service;

import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;
import team.blackjack.service.dto.RevenueResult;

public class RevenueService {

    public RevenueResult getRevenueResult(Map<Player, Result> judgeResults) {
        final double dealerRevenue = calculateDealerRevenue(judgeResults);

        final Map<String, Double> allPlayerRevenue = judgeResults.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().getName(),
                        entry -> calculatePlayerRevenue(entry.getKey(), entry.getValue())
                ));

        return new RevenueResult(dealerRevenue, allPlayerRevenue);
    }

    public double calculatePlayerRevenue(Player player, Result result) {
        return result.getOdds() * player.getBatMoney();
    }

    public double calculateDealerRevenue(Map<Player, Result> judgeResults) {
        return judgeResults.entrySet().stream()
                .mapToDouble(entry -> -calculatePlayerRevenue(entry.getKey(), entry.getValue()))
                .sum() * -1;
    }
}
