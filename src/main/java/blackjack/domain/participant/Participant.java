package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    final String name;
    State state;

    private Participant(final String name, final State state) {
        Objects.requireNonNull(name, "이름에는 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.state = state;
    }

    Participant(final String name, final List<Card> cards) {
        this(name, State.create(new Cards(cards)));
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름에는 공백이 들어올 수 없습니다.");
        }
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    public String getName() {
        return name;
    }

    public void stay() {
        state = state.stay();
    }

    public int calculateScore() {
        checkTurnOver();
        return state.cards().getScore();
    }

    private void checkTurnOver() {
        if (canDraw()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 반환할 수 없습니다.");
        }
    }

    public abstract boolean canDraw();
}
