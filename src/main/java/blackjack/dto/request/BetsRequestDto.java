package blackjack.dto.request;

public record BetsRequestDto(
        int amount
) {

    public static BetsRequestDto from(String amount) {
        try {
            int parsedAmount = Integer.parseInt(amount);
            return new BetsRequestDto(parsedAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 소수점이 없는 양수만 입력해야 합니다.");
        }
    }
}
