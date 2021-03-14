package blackjack.domain.state;

public class Bust extends Finished {
    @Override
    public double earningRate() {
        return 0;
    }

}
