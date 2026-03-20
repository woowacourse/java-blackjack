package dto.request;

public record BettingMoneyRequest(long money) {
    public static BettingMoneyRequest from(String rawInput) {
        return new BettingMoneyRequest(Long.parseLong(rawInput));
    }
}
