package blackjack.model.state;

import blackjack.model.card.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);

        validateBust();
    }

    private void validateBust() {
        if (hand.isNotBust()) {
            throw new IllegalStateException("패가 버스트이지 않습니다.");
        }
    }

    @Override
    public double getEarningRate() {
        return 1;
    }
}
