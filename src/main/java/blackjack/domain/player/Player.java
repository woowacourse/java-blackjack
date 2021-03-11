package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public abstract class Player {

    protected final String name;
    protected State state;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }

    abstract void addCard(Card card);

    abstract boolean canDraw();

    public abstract boolean isDealer();

    public final int calculateScore() {
        return state.calculateScore();
    }

    public final boolean isBust() {
        return state.isBust();
    }

    public final boolean isBlackjack() {
        return state.isBlackjack();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return state.cards();
    }
}
