package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay extends Finished {

    Stay(Cards cards) {
        super(cards);
    }
}
