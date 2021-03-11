package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class State {

    protected Cards cards;

    public State(final Cards cards) {
        this.cards = cards;
    }

    public static State makeState(Cards cards) {
        if (cards.isBlackJack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return cards.isBlackJack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getScore() {
        return cards.getScore();
    }

    public abstract boolean isFinished();

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract double earningRate();

}
