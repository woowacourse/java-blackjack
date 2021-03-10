package blackjack.domain.state;

public class Blackjack extends Finished {
    @Override
    public double profit(double money) {
        return money * 1.5;
    }
}
