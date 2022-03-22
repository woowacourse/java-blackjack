package blackjack.domain.state;

import blackjack.domain.card.Card;

public final class Hit extends Running {

    Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        Cards newCards = cards.add(card);

        if (newCards.isBust()) {
            return new Bust(newCards);
        }

        if (newCards.isReadyToStop()) {
            return new Stay(newCards);
        }

        return new Hit(cards.add(card));
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }
}
