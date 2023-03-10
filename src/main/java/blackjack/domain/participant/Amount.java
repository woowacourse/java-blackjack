package blackjack.domain.participant;

public class Amount {

    private final int value;

    public Amount(int value) {
        validateZero(value);
        this.value = value;
    }

    private static void validateZero(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("1원 이상의 배팅금액을 입력해주세요.");
        }
    }
}
