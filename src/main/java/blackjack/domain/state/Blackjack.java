package blackjack.domain.state;


public class Blackjack extends Finished {

    @Override
    public double earningRate() {
        return 1.5;
    }
}
