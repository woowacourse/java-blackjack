package blackjack.state;

public class BlackJack extends Finished {

    public BlackJack(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State dealerState) {
        if (dealerState.cards().isBlackJack()) {
            return 0;
        }
        return 1.5;
    }
}
