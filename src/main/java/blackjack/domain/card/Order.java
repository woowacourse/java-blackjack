package blackjack.domain.card;

public enum Order {
    YES("y"),
    NO("n");

    private final String value;

    Order(String value) {
        this.value = value;
    }
}
