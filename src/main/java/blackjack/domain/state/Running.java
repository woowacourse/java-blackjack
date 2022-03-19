package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Running extends Started {

    Running(Cards cards) {
        super(cards);
    }

    @Override
    public abstract State draw(Card card);

    @Override
    public abstract State stay();
}
