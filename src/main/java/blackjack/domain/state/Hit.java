package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Hit implements State {

    private final Cards cards;

    protected Hit(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        cards.append(card);

        if (cards.isBust()) {
            return new Bust(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
