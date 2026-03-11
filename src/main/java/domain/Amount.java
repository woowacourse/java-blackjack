package domain;

public record Amount(int amount) {

    public Amount {
        validate(amount);
    }

    private void validate(int amount) {
        validateMin(amount);
        validateUnit(amount);
    }

    private void validateMin(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 1000원 이상부터 가능합니다.");
        }
    }

    private void validateUnit(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 1000원 단위로 가능합니다.");
        }
    }
}
