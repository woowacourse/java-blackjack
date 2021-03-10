package blackjack.domain.state;

public class Stay extends Finished {
    @Override
    public double profit(double money) {
        return money;
    }
}
