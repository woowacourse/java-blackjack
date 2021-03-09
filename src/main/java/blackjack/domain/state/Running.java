package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }
}
