package domain.money;

public class Profit {
    private final double value;

    public Profit(double value) {
        this.value = value;
    }

    public Profit sum(Profit profit) {
        return new Profit(value + profit.getValue());
    }

    public Profit reverse() {
        return new Profit(-value);
    }

    public int getValue() {
        return (int) value;
    }
}
