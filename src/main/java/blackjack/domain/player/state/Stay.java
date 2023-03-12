package blackjack.domain.player.state;

import blackjack.domain.player.Hand;

public class Stay extends Finished {
    Stay(Hand hand) {
        super(hand);
    }
}
