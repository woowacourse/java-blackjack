package domain.player;

import domain.card.Card;
import domain.state.Started;
import domain.state.State;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected State state;


    public final void init(final Card cardA, final Card cardB) {
        state = Started.ofTwoCard(cardA, cardB);
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public final boolean isBust() {
        return state.isBust();
    }

    public final boolean isStand() {
        return state.isStand();
    }

    public final void add(final Card card) {
        state = state.add(card);
    }

    public abstract boolean canHit();

    public void stand() {
        if (!state.isFinished()) {
            state = state.stand();
        }
    }

    public int getScore() {
        return state.getScore();
    }

    public String getName() {
        throw new IllegalCallerException("참여자의 이름이 정해지지 않았습니다");
    }

    public final List<Card> getHands() {
        return Collections.unmodifiableList(state.getHands().getValue());
    }
}
