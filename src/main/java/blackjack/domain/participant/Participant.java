package blackjack.domain.participant;

import blackjack.domain.Money;
import blackjack.domain.carddeck.Card;
import blackjack.domain.state.*;

import java.util.List;

public abstract class Participant {

    protected State state;
    protected Money money;

    protected Participant() {
    }

    public void receiveFirstHand(List<Card> cards) {
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
        return state.toHandList();
    }

    public boolean isFinished() {
        return this.state.isFinished();
    }

    public void stay() {
        this.state = state.stay();
    }

    public boolean isBlackjack() {
        return this.state instanceof Blackjack;
    }

    public boolean isStay() {
        return this.state instanceof Stay;
    }

    public boolean isBust() {
        return this.state instanceof Bust;
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

    public void initMoney(Money money) {
        this.money = money;
    }
}
