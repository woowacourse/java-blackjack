package blackjack.domain.state;

public class Bust extends Finished {

    public Bust() {
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
