package blackjack.domain.state;

public class Stay extends Finished {

    @Override
    public double earningRate() {
        return 1;
    }
}
