package domain.card;

public record Card(CardShape shape, CardNumber number) {
    public int value() {
        return number.value();
    }
}
