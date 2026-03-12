package domain;

public record Profit(double value) {
    public int toInt() {
        return (int) value;
    }

    public Profit negate() {
        return new Profit(value * -1);
    }
}
