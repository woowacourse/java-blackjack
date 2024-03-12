package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class PlayerStatus {
    private static final int GAIN_BETTING = 1;
    private static final int LOSE_BETTING = -1;

    private final Hand hand;
    private int money;

    public PlayerStatus() {
        hand = new Hand();
        this.money = 0;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void subtractMoney(int money) {
        this.money -= money;
    }

    public void calculateWinMoney() {
        money *= GAIN_BETTING;
    }

    public void calculateLoseMoney() {
        money *= LOSE_BETTING;
    }

    public int getMoney() {
        return money;
    }

    public Hand getHand() {
        return hand;
    }
}
