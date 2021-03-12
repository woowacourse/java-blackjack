package blackjack.domain.state;

public class Blackjack extends Finished {
    private static final double RATIO = 1.5;

    @Override
    public double profit(double money) {
        return money * RATIO;
    }
}
