package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Stand extends Finished {

    Stand(Cards cards) {
        super(cards);
    }
}
