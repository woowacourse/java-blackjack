package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

public abstract class Participants {

    private static final int MIN_NAME_LENGTH = 1;

    private final String name;
    protected State state;
    protected double money;

    public Participants(String name) {
        validateNameLength(name);
        this.name = name;
    }

    public Participants(String name, State state) {
        validateNameLength(name);
        this.name = name;
        this.state = state;
    }

    public void firstDraw(Cards cards) {
        this.state = StateFactory.draw(cards);
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    private void validateNameLength(String name) {
        if (name.trim().length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("유효하지 않은 이름입니다.");
        }
    }

    public Cards getTakenCards() {
        return state.cards();
    }

    public int score() {
        return state.score();
    }

    public String getName() {
        return name;
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isStay() {
        return state.isStay();
    }

    public boolean isHit() {
        return state.isHit();
    }

    public abstract boolean canDraw();
}
