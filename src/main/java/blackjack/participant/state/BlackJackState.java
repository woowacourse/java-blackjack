package blackjack.participant.state;

import blackjack.participant.Hand;

public class BlackJackState extends TerminatedState {

    public BlackJackState(Hand hand) {
        super(hand);
    }
}
