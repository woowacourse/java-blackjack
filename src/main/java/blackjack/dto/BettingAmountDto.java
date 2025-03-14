package blackjack.dto;

public record BettingAmountDto(
    double bettingAmount
) {

    public static BettingAmountDto from(String input) {
        try {
            return new BettingAmountDto(Double.parseDouble(input));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 배팅 금액입니다.");
        }
    }
}
