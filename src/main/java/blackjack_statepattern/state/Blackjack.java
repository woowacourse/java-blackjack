package blackjack_statepattern.state;

import blackjack_statepattern.Cards;

public class Blackjack extends Finished {
    private final Cards cards;

    Blackjack(Cards cards) {
        this.cards = cards;
    }
}
