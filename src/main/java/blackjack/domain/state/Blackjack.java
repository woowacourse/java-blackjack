package blackjack.domain.state;

public class Blackjack extends Finished {
    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public double profitRatio() {
        return 1.5;
    }
}
