package domain;

public record Profit(double value) {
    public int toInt() {
        return (int) value;
    }
}
