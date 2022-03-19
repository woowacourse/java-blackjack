package blackjack_statepattern.state;

import blackjack_statepattern.Cards;

public final class Stay extends Finished {
    Stay(final Cards cards) {
        super(cards);
    }
}
