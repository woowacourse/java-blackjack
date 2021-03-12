package blackjack.domain.state;

public class Stay extends Finished {
    private static final double RATIO = 1.0;

    @Override
    public double profit(double money) {
        return money * RATIO;
    }
}
