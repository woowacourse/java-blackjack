package blackjack.domain.state;

public class Bust extends Finished {
    private static final double RATIO = 0.0;

    @Override
    public double profit(double money) {
        return money * RATIO;
    }
}
