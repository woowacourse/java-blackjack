package domain;

public record BettingRecord(
        BettingMoney bettingMoney,
        GameResultStatus gameResultStatus
) {

    public BettingRecord(BettingMoney bettingMoney) {
        this(bettingMoney, GameResultStatus.IN_PROGRESS);
    }
}
