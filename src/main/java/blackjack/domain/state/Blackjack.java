package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }
}
