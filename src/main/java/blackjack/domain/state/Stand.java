package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stand extends Finished {

    Stand(Cards cards) {
        super(cards);
    }
}
