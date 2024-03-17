package domain;

public class Profit {
    private final double value;

    public Profit(double value) {
        this.value = value;
    }

    public int getValue() {
        return (int) value;
    }
}
