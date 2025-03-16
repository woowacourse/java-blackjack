package domain;

public record BettingHistory(
        BettingMoney bettingMoney,
        GameResultStatus gameResultStatus
) {

    public BettingHistory(BettingMoney bettingMoney) {
        this(bettingMoney, GameResultStatus.IN_PROGRESS);
    }
}
