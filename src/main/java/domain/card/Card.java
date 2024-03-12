package domain.card;

public record Card(Shape shape, Number number) {
    public int getNumberValue() {
        return number.getValue();
    }

    public boolean isAce() {
        return number.isAce();
    }
}
