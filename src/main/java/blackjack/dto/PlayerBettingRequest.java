package blackjack.dto;

public record PlayerBettingRequest(
    String playerNickname,
    long amount
) {

    public static PlayerBettingRequest of(String playerNickname, String rawAmount) {
        long amount = validateAmount(rawAmount);
        return new PlayerBettingRequest(playerNickname, amount);
    }

    private static long validateAmount(String rawAmount) {
        long amount;
        try {
            amount = Long.parseLong(rawAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 숫자가 입력되어야 합니다.");
        }
        return amount;
    }
}
