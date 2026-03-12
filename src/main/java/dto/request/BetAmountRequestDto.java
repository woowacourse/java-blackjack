package dto.request;

public record BetAmountRequestDto(String betAmount) {
    public BetAmountRequestDto {
        validateBetAmountIsNotNullAndIsNotBlank(betAmount);
    }

    private void validateBetAmountIsNotNullAndIsNotBlank(String betAmount) {
        if (betAmount == null || betAmount.isBlank()) {
            throw new IllegalArgumentException("금액 입력은 null 또는 공백이면 안됩니다");
        }
    }
}
