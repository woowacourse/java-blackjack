package domain;

public record BettingMoney(int bettingMoney) {

    public BettingMoney {
        validateMoney(bettingMoney);
    }


    private void validateMoney(int value) {
        if (isNotNatural(value)) {
            throw new IllegalArgumentException("베팅 금액은 자연수여야 합니다.");
        }

        if (isInvalidUnit(value)) {
            throw new IllegalArgumentException("배팅 금액의 단위는 10000원 입니다.");
        }
    }

    private boolean isNotNatural(int value) {
        return value <= 0;
    }

    private boolean isInvalidUnit(int value) {
        return value % 10_000 != 0;
    }
}
