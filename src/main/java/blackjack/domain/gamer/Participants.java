package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;

public abstract class Participants {

    private static final int MIN_NAME_LENGTH = 1;

    private final String name;
    protected State state;

    public Participants(String name) {
        validateNameLength(name);
        this.name = name;
        this.state = new Hit(new Cards());
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

    public abstract boolean canDraw();
}
