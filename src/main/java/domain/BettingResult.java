package domain;

public class BettingResult {

    private final BettingMoney bettingMoney;
    private final GameResultStatus gameResultStatus;

    public BettingResult(BettingMoney bettingMoney, GameResultStatus gameResultStatus) {
        this.bettingMoney = bettingMoney;
        this.gameResultStatus = gameResultStatus;
    }

    public BettingResult(BettingMoney bettingMoney) {
        this(bettingMoney, GameResultStatus.IN_PROGRESS);
    }
}
