package blackjack_statepattern.state;

import blackjack_statepattern.Cards;

public final class Bust extends Finished {
    private final Cards cards;

    Bust(Cards cards) {
        this.cards = cards;
    }
}
