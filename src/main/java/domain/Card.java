package domain;

public record Card(CardShape cardShape, CardNumber cardNumber) {
    public int value() {
        return cardNumber.value();
    }
}
