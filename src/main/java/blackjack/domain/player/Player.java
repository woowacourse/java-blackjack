package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.*;

public abstract class Player {
    private final Name name;
    protected State state;

    protected Player(Name name, Cards cards) {
        this.name = name;
        this.state = StateFactory.drawFirstCards(cards);
    }

    public abstract boolean canDraw();

    public String getName() {
        return name.getName();
    }

    public boolean isBlackjack() {
        return state instanceof Blackjack;
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public void stay() {
        if (isHit()) {
            state = state.stay();
        }
    }

    private boolean isHit() {
        return state instanceof Hit;
    }

    public abstract void draw(Card card);

    public int calculateScore() {
        return state.cards().calculateScore();
    }

    public Cards cards() {
        return state.cards();
    }
}
