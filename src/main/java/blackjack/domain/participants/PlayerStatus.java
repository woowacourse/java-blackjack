package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class PlayerStatus {
    private static final int GAIN_BETING = 2;
    private static final int LOSE_BETING = -1;

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

    public void calculateWinMoney() {
        money *= GAIN_BETING;
    }

    public void calculateLoseMoney() {
        money *= LOSE_BETING;
    }

    public int getMoney() {
        return money;
    }

    public Hand getHand() {
        return hand;
    }
}
