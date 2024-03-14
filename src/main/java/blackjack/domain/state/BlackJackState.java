package blackjack.domain.state;

import blackjack.domain.Hand;

public class BlackJackState extends ClosedState {

    public BlackJackState(Hand hand) {
        super(hand);
    }
}
