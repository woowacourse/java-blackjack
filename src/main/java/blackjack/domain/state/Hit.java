package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Hit extends Running {

    Hit() {
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    public abstract State draw(Card card);
}
