package blackjack.domain.state;

public class BustState extends ClosedState {

    BustState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate(Hand other) {
        return -1;
    }
}
