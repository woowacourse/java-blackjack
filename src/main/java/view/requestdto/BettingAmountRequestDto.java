package view.requestdto;

public record BettingAmountRequestDto(String bettingAmount) {

    public static final int MINIMUM_BETTING_AMOUNT = 10;

    public BettingAmountRequestDto {
        validateBettingAmountIsNotNullAndNotBlank(bettingAmount);
        validateBettingAmountIsDigit(bettingAmount);
        validateBettingAmountIsOverMinimumAmount(bettingAmount);
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
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
    }

    private void validateBettingAmountIsOverMinimumAmount(String bettingAmount) {
        if (Integer.parseInt(bettingAmount) < MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException(
                    "[ERROR] 최소 베팅 금액은 " + MINIMUM_BETTING_AMOUNT + " 입니다");
        }
    }

    public int getBettingAmount() {
        return Integer.parseInt(bettingAmount);
    }
}
