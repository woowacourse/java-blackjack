package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public class Blackjack extends Finished {

    Blackjack(PlayerCards cards) {
        this.cards = cards;
    }
}
