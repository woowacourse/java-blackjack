package blackjackgame.domain.state;

import blackjackgame.domain.card.Hand;

public final class BlackJack extends Finished {

    BlackJack(Hand hand) {
        super(hand);
    }
}
