package domain;

public record Money(int value) {
    public Money {
        if (value < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수로 설정할 수 없습니다.");
        }
    }

    public static Money zero() {
        return new Money(0);
    }
}
