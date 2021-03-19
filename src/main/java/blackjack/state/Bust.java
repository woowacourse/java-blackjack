package blackjack.state;

public class Bust extends Finished {

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State dealerState) {
        return -1;
    }
}
