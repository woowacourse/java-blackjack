package blackjack.domain.participant;

import blackjack.domain.carddeck.Card;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final Hand hand;
    protected State state;

    protected Participant() {
        this.hand = new Hand();
        this.state = new Hit();
    }

    public abstract boolean isOverLimitScore();

    public abstract void receiveCard(final Card card);

    public void validateState() {
        if (this.state.isFinished()) {
            throw new IllegalStateException("현재 상태에서는 카드를 더 받을 수 없습니다.");
        }
    }

    public Score getTotalScore() {
        return hand.totalScore();
    }

    public List<Card> toHandList() {
        return Collections.unmodifiableList(hand.toList());
    }

    public void receiveFirstHand(List<Card> cards) {
        cards.forEach(hand::addCard);
        this.state = StateFactory.initState(this.hand);
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public void stay() {
        this.state = state.stay();
    }
}
