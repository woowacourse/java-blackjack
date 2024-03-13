package domain;

public class Profit {

    private final int value;

    public Profit(int battingAmount) {
        this.value = battingAmount;
    }

    public Profit lose() {
        return new Profit(-value);
    }

    public Profit keep() {
        return new Profit(0);
    }

    public Profit win() {
        return new Profit(value);
    }

    public Profit specialWin() {
        return new Profit((int) (value * 1.5));
    }
}
