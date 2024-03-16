package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public class BlackJackState extends ClosedState {

    BlackJackState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate(Hand other) {
        if (other.isBlackJack()) {
            return 1;
        }
        return 1.5;
    }
}
