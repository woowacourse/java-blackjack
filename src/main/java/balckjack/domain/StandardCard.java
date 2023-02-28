package balckjack.domain;

public class StandardCard extends Card {

    public static final int MAX_BOUND_VALUE = 10;
    public static final int MIN_BOUND_VALUE = 2;

    public StandardCard(int value) {
        super(String.valueOf(value), value);
    }

    @Override
    void validateValue(int value) {
        if (value > MAX_BOUND_VALUE || value < MIN_BOUND_VALUE) {
            throw new IllegalArgumentException(
                String.format("일반 카드의 번호는 2에서 10 사이여야 합니다. 입력값: %d", value));
        }
    }
}
