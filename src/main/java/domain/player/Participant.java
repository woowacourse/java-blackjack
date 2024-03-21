package domain.player;

import domain.card.Card;
import domain.state.State;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected State state;

    public final void add(final Card card) {
        state = state.add(card);
    }

    final void standIfRunning() {
        if (state.isFinished()) {
            return;
        }
        state = state.stand();
    }

    State getState() {
        return state;
    }

    public final int getScore() {
        return state.getScore();
    }

    public String getName() {
        throw new IllegalCallerException("참여자의 이름이 정해지지 않았습니다");
    }

    public final List<Card> getHands() {
        return Collections.unmodifiableList(state.getHands().getValue());
    }
}
