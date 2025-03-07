package domain;

public record TrumpCard(CardShape cardShape, CardNumber cardNumber) {
    public int getCardNumberValue() {
        return cardNumber.getValue();
    }
}
