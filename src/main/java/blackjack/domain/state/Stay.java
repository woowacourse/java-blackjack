package blackjack.domain.state;

public class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate() {
        return 1.0d;
    }
}
