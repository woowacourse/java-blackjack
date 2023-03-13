package domain;

public class PlayerScore {

    private final String name;
    private final Profit profit;

    public PlayerScore(String name, Profit profit) {
        this.name = name;
        this.profit = profit;
    }

    public Double getProfit() {
        return profit.getProfit();
    }

    //
//    private final String name;
//    private final GameResult gameResult;
//
//    public PlayerScore(String name, GameResult gameResult) {
//        this.name = name;
//        this.gameResult = gameResult;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public GameResult getGameResult() {
//        return gameResult;
//    }
}
