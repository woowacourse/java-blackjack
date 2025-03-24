package user;

import card.Card;
import card.CardHand;
import java.util.List;

public abstract class Participant {
    protected final CardHand cardHand;
    protected double betMoney;

    protected Participant() {
        this.cardHand = new CardHand();
        this.betMoney = 0;
    }

    public abstract boolean isDrawable();

    public abstract List<Card> openInitialCard();

    public abstract String getName();

    public abstract boolean isPlayer();

    public void drawCard(Card card) {
        cardHand.add(card);
    }

    public int getSize() {
        return cardHand.size();
    }

    public void betMoney(double money) {
        this.betMoney = money;
    }

    public double getBetMoney() {
        return this.betMoney;
    }

    public int calculateScore() {
        return this.cardHand.calculateScore();
    }

    public boolean isBlackjack() {
        return this.cardHand.isBlackjack();
    }

    public void addTrumpCard(Card card) {
        this.cardHand.add(card);
    }

    public boolean isBust() {
        return this.cardHand.checkOverScore();
    }

    public List<Card> openAllCard() {
        return this.cardHand.getAllCard();
    }

    public double calculateReward(double rewardRate) {
        return this.betMoney * rewardRate;
    }
}
