package domain;

import vo.Money;

public class User {
    private final String userName;
    private final Hand hand;
    private final Money bettingMoney;

    public User(String name, Money bettingMoney) {
        this.userName = name;
        this.bettingMoney = bettingMoney;
        this.hand = new Hand();
    }

    public void receiveCard(Card card) {
        hand.saveCard(card);
    }

    public String getName() {
        return userName;
    }

    public String getCardsDisplay() {
        return hand.getCardsDisplay();
    }

    public void calculateScore() {
        hand.calculateHandScore();
    }

    public int getTotalScore() {
        return hand.getHandTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }
}
