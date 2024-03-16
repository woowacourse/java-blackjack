package domain.player;

public class Profit {
    private final double value;

    public Profit(final double earningRate, final int betAmount) {
        value = earningRate * betAmount;
    }


    public double win() {
        return value;
    }

    public double lose() {
        return -1.0 * value;
    }

    public double tie() {
        return 0;
    }
}
