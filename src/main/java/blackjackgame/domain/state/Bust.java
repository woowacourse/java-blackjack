package blackjackgame.domain.state;

import blackjackgame.domain.card.Hand;

public final class Bust extends Finished {

    Bust(Hand hand) {
        super(hand);
    }
}
