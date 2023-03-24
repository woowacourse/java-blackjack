package domain.result;

import java.util.List;

public class DealerScore {

    private final String name;
    private final Profit profit;

    private DealerScore(String name, Profit profit) {
        this.name = name;
        this.profit = profit;
    }

    public static DealerScore generateDealerScore(List<PlayerScore> playerScores) {
        double sumOfPlayerProfit = playerScores.stream()
                .mapToDouble(PlayerScore::getProfit)
                .sum();
        double dealerProfit = -sumOfPlayerProfit;
        return new DealerScore("딜러", Profit.dealerProfit(dealerProfit));
    }

    public Double getProfit() {
        return profit.getProfit();
    }
}

