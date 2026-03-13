package domain;

import view.Message;
import vo.GameResult;
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

    public Money calculateProfit(GameResult gameResult) {
        return gameResult.calculateProfit(this.bettingMoney);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
