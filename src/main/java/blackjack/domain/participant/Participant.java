package blackjack.domain.participant;

import blackjack.domain.money.Money;
import blackjack.domain.result.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.ArrayList;

public abstract class Participant {

    private static final int INIT_MONEY = 0;

    private Hand hand;
    private Money money;

    public Participant() {
        this.hand = new Hand(new ArrayList<>());
        this.money = new Money(INIT_MONEY);
    }

    public void receiveCard(final Card card) {
        this.hand = this.hand.add(card);
    }

    public void receiveHand(final Hand hand) {
        this.hand = hand;
    }

    public void receiveMoney(final Money money) {
        this.money = this.money.add(money);
    }

    public int totalScore() {
        return this.hand.totalScore();
    }

    public boolean isBust() {
        return this.hand.isBust();
    }

    public boolean isBlackjack() {
        return this.hand.isBlackjack();
    }

    public Result compareHandTo(Hand anotherHand) {
        return this.hand.compareHandTo(anotherHand);
    }

    public Hand getHand() {
        return hand;
    }

    public int getMoney() {
        return money.getMoney();
    }
}
