package domain.player;

import domain.card.Card;
import domain.state.Started;
import domain.state.State;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected State state;


    public final void init(final Card cardA, final Card cardB) {
        this.state = Started.ofTwoCard(cardA, cardB);
    }

    public final boolean isFinished() {
        return this.state.isFinished();
    }

    public final boolean isNotFinished() {
        return !isFinished();
    }


    public int getScore() {
        return this.state.getScore();
    }

    public final boolean isBust() {
        return this.state.isBust();
    }

    public final void add(final Card card) {
        this.state = this.state.add(card);
    }

    public abstract boolean canHit();


    public String getName() {
        throw new IllegalCallerException("참여자의 이름이 정해지지 않았습니다");
    }

    public final List<Card> getHands() {
        return Collections.unmodifiableList(state.getHands().getValue());
    }
}
