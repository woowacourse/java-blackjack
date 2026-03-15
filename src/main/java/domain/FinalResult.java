package domain;

public class FinalResult {
    private final Player player;
    private final ResultType resultType;
    
    private FinalResult(Player player, ResultType resultType) {
        this.player = player;
        this.resultType = resultType;
    }

    public static FinalResult from(Player player, ResultType resultType) {
        return new FinalResult(player, resultType);
    }

    public Name getName() {
        return player.getName();
    }

    public ResultType getResultType() {
        return resultType;
    }

    public int getPlayerBettingAmount() {
        return player.getBettingAmount();
    }

    public int getProfit() {
        return resultType.calculateProfit(getPlayerBettingAmount());
    }

}