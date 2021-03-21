package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.domain.state.hand.Hand;
import blackjack.domain.state.hand.Score;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected State state;
    protected final Money money;
    private final Name name;

    protected Participant(Name name, Money money) {
        this.name = name;
        this.money = money;
    }

    public void receiveFirstHand(final List<Card> cards) {
        this.state = StateFactory.initHand(cards);
    }

    public abstract boolean isOverLimitScore();

    public abstract void receiveCard(final Card card);

    public Score getTotalScore() {
        return state.totalScore();
    }

    public Hand hand() {
        return state.hand();
    }

    public List<Card> toHandList() {
        return Collections.unmodifiableList(state.toHandList());
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean isBlackjack() {
        return this.state.isBlackJack();
    }

    public boolean isStay() {
        return this.state.isStay();
    }

    public boolean isBust() {
        return this.state.isBust();
    }

    public boolean isHit() {
        return this.state.isHit();
    }

    public boolean isHigherThan(Participant participant) {
        return this.getTotalScore().compareTo(participant.getTotalScore());
    }

    public double profit() {
        return this.state.profit(money.value());
    }

    public double profit(double earningRate) {
        return money.value() * earningRate;
    }

    public String getName() {
        return this.name.getValue();
    }
}
