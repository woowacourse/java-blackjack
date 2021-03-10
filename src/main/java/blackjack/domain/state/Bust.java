package blackjack.domain.state;

public class Bust extends Finished{
    @Override
    public double profit(double money) {
        return 0;
    }
}
