package dto;

import view.InputView;

public record BettingAmountRequestDto(String bettingAmount) {

    public BettingAmountRequestDto {
        validateBettingAmountIsNotNullAndNotBlank(bettingAmount);
        validateBettingAmountIsDigit(bettingAmount);
        validateBettingAmountIsPositive(bettingAmount);
    }

    private void validateBettingAmountIsNotNullAndNotBlank(String bettingAmount) {
        if (bettingAmount == null || bettingAmount.isBlank()) {
            throw new IllegalArgumentException("베팅 금액은 null 또는 공백이면 안 됩니다.");
        }
    }

    private void validateBettingAmountIsDigit(String bettingAmount) {
        try {
            Integer.parseInt(bettingAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");        }
    }

    private void validateBettingAmountIsPositive(String bettingAmount) {
        if(Integer.parseInt(bettingAmount) <= 0) {
            throw new IllegalArgumentException("[ERROR] 양수만 입력 가능합니다.");
        }
    }
}
