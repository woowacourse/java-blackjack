package blackjack.dto;

public record DealerGameResult(
    long profit
) {

    public static DealerGameResult from(long profit) {
        return new DealerGameResult(profit);
    }
}
