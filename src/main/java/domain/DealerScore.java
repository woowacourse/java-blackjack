package domain;

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

    //
//    private final Map<GameResult, Integer> result = new EnumMap<>(GameResult.class);
//
//    public DealerScore(List<PlayerScore> results) {
//        init(results);
//    }
//
//    public void init(List<PlayerScore> results) {
//        for (PlayerScore playerScore : results) {
//            GameResult reverseResult = playerScore.getGameResult().reverse();
//            result.put(reverseResult, result.getOrDefault(reverseResult, 0) + 1);
//        }
//    }
//
//    public int getWin() {
//        return result.getOrDefault(GameResult.WIN, 0);
//    }
//
//    public int getLose() {
//        return result.getOrDefault(GameResult.LOSE, 0);
//    }
//
//    public int getDraw() {
//        return result.getOrDefault(GameResult.DRAW, 0);
//    }
}
