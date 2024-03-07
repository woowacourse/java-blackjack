package domain;

public record Card(CardShape shape, CardNumber number) {
    public int value() {
        return number.value();
    }
}
