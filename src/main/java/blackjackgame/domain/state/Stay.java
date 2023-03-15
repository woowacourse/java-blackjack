package blackjackgame.domain.state;

import blackjackgame.domain.card.Hand;

public final class Stay extends Finished {
    Stay(Hand hand) {
        super(hand);
    }
}
