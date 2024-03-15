package blackjack.domain.participants;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.Hand;

public class PlayerStatus {
    private final Hand hand;
    private GamblingMoney gamblingMoney;

    public PlayerStatus() {
        this.hand = new Hand();
        this.gamblingMoney = new GamblingMoney();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public void addMoney(GamblingMoney gamblingMoney) {
        this.gamblingMoney = this.gamblingMoney.add(gamblingMoney);
    }

    public void subtractMoney(GamblingMoney gamblingMoney) {
        this.gamblingMoney = this.gamblingMoney.subtract(gamblingMoney);
    }

    public void calculateBettingMoney(float benefit) {
        this.gamblingMoney = this.gamblingMoney.multiply(benefit);
    }

    public int checkHandSize() {
        return hand.size();
    }

    public GamblingMoney getGamblingMoney() {
        return gamblingMoney;
    }

    public Hand getHand() {
        return hand;
    }
}
