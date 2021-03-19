package blackjack.state;

public class Stay extends Finished {

    protected Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(State dealerState) {
        if (isBlackJack(dealerState) ||
                (isNotDealerBust(dealerState) && isDealerScoreHigherThanPlayerScore(dealerState))) {
            return -1;
        }
        return 1;
    }

    private boolean isDealerScoreHigherThanPlayerScore(final State dealerState) {
        return dealerState.cards().score().getScore() > this.cards().score().getScore();
    }

    private boolean isNotDealerBust(final State dealerState) {
        return !dealerState.cards().isBust();
    }

    private boolean isBlackJack(final State dealerState) {
        return dealerState.cards().isBlackJack();
    }


}
