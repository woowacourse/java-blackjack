package blackjack.domain.game;

import java.util.Arrays;

public enum Order {

    YES("y"),
    NO("n");

    private static final String INVALID_VALUE_MESSAGE = "해당 값에 해당하는 상수가 존재하지 않습니다.";

    private final String value;

    Order(final String value) {
        this.value = value;
    }

    public static Order from(final String value) {
        return Arrays.stream(Order.values())
                .filter(order -> order.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_VALUE_MESSAGE));
    }

    public boolean isHit() {
        return this.equals(Order.YES);
    }

    public String getValue() {
        return value;
    }
}
