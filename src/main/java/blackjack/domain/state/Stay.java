package blackjack.domain.state;

import blackjack.domain.user.Cards;

public class Stay extends Finished {
    public Stay(Cards cards) {
        super(cards);
    }
}
