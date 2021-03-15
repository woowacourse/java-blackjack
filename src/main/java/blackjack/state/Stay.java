package blackjack.state;

public class Stay extends Finished {

    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State dealerState) {
        if (dealerState.cards().isBlackJack() ||
                (!dealerState.cards().isBust() && dealerState.cards().score().getScore() > this.cards().score().getScore())) {
            return -1;
        }
        return 1;
    }


}
