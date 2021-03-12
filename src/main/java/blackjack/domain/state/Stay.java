package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay extends Finish {
    protected Stay(Cards cards) {
        super(cards);
    }
}
