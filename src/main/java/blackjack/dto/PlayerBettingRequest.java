package blackjack.dto;

public record PlayerBettingRequest(
    String playerNickname,
    int amount
) {

    public static PlayerBettingRequest of(String playerNickname, String rawAmount) {
        int amount;
        try {
            amount = Integer.parseInt(rawAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금액은 숫자가 입력되어야 합니다.");
        }
        return new PlayerBettingRequest(playerNickname, amount);
    }
}
