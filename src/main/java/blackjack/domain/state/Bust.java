package blackjack.domain.state;

public class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return -1;
    }
}
