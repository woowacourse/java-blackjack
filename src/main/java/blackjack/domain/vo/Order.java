package blackjack.domain.vo;

public enum Order {
    YES("y"),
    NO("n");

    private final String value;

    Order(String value) {
        this.value = value;
    }
}
