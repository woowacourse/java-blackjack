package domain.player;

public class Profit {
    public static final double NEGATIVE_OFFSET = -1.0;
    private final double value;

    public Profit(final double earningRate, final int betAmount) {
        value = earningRate * betAmount;
    }

    public static double reverse(final double totalSum) {
        return NEGATIVE_OFFSET * totalSum;
    }


    public double win() {
        return value;
    }

    public double lose() {
        return NEGATIVE_OFFSET * value;
    }

    public double tie() {
        return 0;
    }
}


