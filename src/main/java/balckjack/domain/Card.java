package balckjack.domain;

abstract public class Card {

    public static final int MAX_BOUND_VALUE = 11;
    public static final int MIN_BOUND_VALUE = 1;

    private final String name;
    private final int value;

    public Card(String name, int value) {
        validateValue(value);
        this.name = name;
        this.value = value;
    }

    protected void validateValue(int value) {
        if (value > MAX_BOUND_VALUE || value < MIN_BOUND_VALUE) {
            throw new IllegalArgumentException(
                String.format("카드의 번호는 1에서 11 사이여야 합니다. 입력값: %d", value));
        }
    }

}
