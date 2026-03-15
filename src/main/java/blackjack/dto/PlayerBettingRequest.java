package blackjack.dto;

public record PlayerBettingRequest(
    String playerNickname,
    long amount
) {

    private static final long INITIAL_AMOUNT = 0;

    public static PlayerBettingRequest createInitialRequest(String playerName) {
        validateNickname(playerName);
        return new PlayerBettingRequest(playerName, INITIAL_AMOUNT);
    }

    public static PlayerBettingRequest of(String playerNickname, String rawAmount) {
        long amount = validateAmount(rawAmount);
        return new PlayerBettingRequest(playerNickname, amount);
    }

    private static void validateNickname(String playerNickname) {
        if (playerNickname == null || playerNickname.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
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
