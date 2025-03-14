package domain;

public record Money(int value) {
    public Money {
        if (value <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0 이하의 숫자로 설정할 수 없습니다.");
        }
    }
}
