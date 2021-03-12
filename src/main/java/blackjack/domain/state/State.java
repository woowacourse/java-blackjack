package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.finished.Blackjack;
import blackjack.domain.state.running.Hit;

public abstract class State {

    protected Cards cards;

    public State() {
        this.cards = new Cards();
    }

    public State(final Cards cards) {
        this.cards = cards;
    }

    public State initialDraw(Deck deck) {
        Cards cards = new Cards();
        cards.initialDraw(deck);

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
