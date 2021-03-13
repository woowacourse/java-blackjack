package blackjack.domain.state;

public class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }

    public double earningRate() {
        return 1.5d;
    }
}
