package blackjack.player.state;

import blackjack.player.Hand;

public class BlackJackState extends TerminatedState {

    public BlackJackState(Hand hand) {
        super(hand);
    }
}
