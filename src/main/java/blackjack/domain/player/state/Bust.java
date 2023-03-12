package blackjack.domain.player.state;

import blackjack.domain.player.Hand;

public class Bust extends Finished {
    Bust(Hand hand) {
        super(hand);
    }
}
