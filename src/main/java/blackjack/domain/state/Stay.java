package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public class Stay extends Finished {

    Stay(PlayerCards cards) {
        this.cards = cards;
    }
}
