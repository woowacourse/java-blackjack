package domain.card;

public record Card(Shape shape, Number number) {
    public int getNumberValue() {
        return number.getNumber();
    }

    public String getName() {
        return number.getName() + shape.getName();
    }
}
