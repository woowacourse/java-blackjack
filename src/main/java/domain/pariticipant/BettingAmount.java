package domain.pariticipant;

public record BettingAmount(long bettingAmount) {

    public BettingAmount {
        if (bettingAmount <= 0 || bettingAmount > 1_000_000_000) {
            throw new IllegalArgumentException("");
        }
    }
}
