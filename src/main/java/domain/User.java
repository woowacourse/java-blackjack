package domain;

import view.Message;
import vo.GameResult;
import vo.Money;

public class User {
    private final String userName;
    private final Hand hand;
    private final Money bettingMoney;
    private Money profit = new Money(0);

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

    public String formatAskGetExtraCard() {
        return String.format(Message.REQUEST_GET_EXTRA_CARD, userName);
    }

    public void calculateScore() {
        hand.calculateHandScore();
    }

    public String getUserFinalDisplay() {
        return hand.getFinalDisplay();
    }

    public int getTotalScore() {
        return hand.getHandTotalScore();
    }

    public Money updateProfitBy(GameResult gameResult) {
        Money earnedMoney = gameResult.calculateProfit(this.bettingMoney);
        this.profit = earnedMoney;
        return earnedMoney;
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
