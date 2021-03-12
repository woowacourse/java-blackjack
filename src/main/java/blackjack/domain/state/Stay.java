package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay extends Finish {
    public Stay(Cards cards) {
        super(cards);
    }
}
