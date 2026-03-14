package blackjack.model.state;

import blackjack.model.card.Hand;

public class Blackjack extends Finished {

    public Blackjack(Hand hand) {
        super(hand);

        validateBlackjack();
    }

    @Override
    public double getEarningRate() {
        return 1.5;
    }

    private void validateBlackjack() {
        if (hand.isNotBlackjack()) {
            throw new IllegalStateException("패가 블랙잭이지 않습니다.");
        }
    }
}
