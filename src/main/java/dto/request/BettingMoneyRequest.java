package dto.request;

public record BettingMoneyRequest(long money) {
    public static BettingMoneyRequest from(String rawInput) {
        try {
            return new BettingMoneyRequest(Long.parseLong(rawInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 베팅 금액 형식입니다.");
        }
    }
}
