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

    public boolean isBlackJack() {
        return Blackjack.class.isInstance(state);
    }

    public boolean isBust() {
        return Bust.class.isInstance(state);
    }

    public void stay() {
        if (isHit()) {
            state = state.stay();
        }
    }

    private boolean isHit() {
        return Hit.class.isInstance(state);
    }

    public abstract void draw(Card card);

    public int calculateScore() {
        return state.cards().calculateScore();
    }

    public Cards cards() {
        return state.cards();
    }
}
