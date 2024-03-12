package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class PlayerStatus {
    private static final int GAIN_BETTING = 1;
    private static final int LOSE_BETTING = -1;


    private final Hand hand;
    private Money money;

    public PlayerStatus() {
        hand = new Hand();
        this.money = new Money();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public void addMoney(Money money) {
        this.money = this.money.add(money);
    }

    public void subtractMoney(Money money) {
        this.money = this.money.subtract(money);
    }

    public void calculateWinMoney() {
        this.money = this.money.multiply(GAIN_BETTING);
    }

    public void calculateLoseMoney() {
        this.money = this.money.multiply(LOSE_BETTING);
    }

    public Money getMoney() {
        return money;
    }

    public Hand getHand() {
        return hand;
    }
}
