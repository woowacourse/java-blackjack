package dto;

public record BettingAmountRequestDto(String bettingAmount) {

    public BettingAmountRequestDto {
        validateBettingAmountIsNotNullAndNotBlank(bettingAmount);
    }

    private void validateBettingAmountIsNotNullAndNotBlank(String bettingAmount) {
        if (bettingAmount == null || bettingAmount.isBlank()) {
            throw new IllegalArgumentException("베팅 금액은 null 또는 공백이면 안 됩니다.");
        }
    }
}
