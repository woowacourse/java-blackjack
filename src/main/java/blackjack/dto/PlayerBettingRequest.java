package blackjack.dto;

public record PlayerBettingRequest(
    String playerNickname,
    int amount
) {

    public static PlayerBettingRequest of(String playerNickname, String rawAmount) {
        int amount = validateAmount(rawAmount);
        validateNickname(playerNickname);
        return new PlayerBettingRequest(playerNickname, amount);
    }

    private static void validateNickname(String playerNickname) {
        if (playerNickname == null || playerNickname.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
        }
    }

    private static int validateAmount(String rawAmount) {
        int amount;
        try {
            amount = Integer.parseInt(rawAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 숫자가 입력되어야 합니다.");
        }
        return amount;
    }
}
