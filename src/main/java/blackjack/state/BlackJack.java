package blackjack.state;

public class BlackJack extends Finished {

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}
