package model.player;

import java.util.List;
import model.Outcome;
import model.card.Card;

public class Participant extends User {

    private Integer bettingAmount;

    public Participant(String name, List<Card> cards, Integer bettingAmount) {
        super(name, cards);
        this.bettingAmount = bettingAmount;
    }

    public Participant(String name, List<Card> cards) {
        super(name, cards);
    }

    public Double calculateProfit(Dealer dealer) { //todo 조건문을 Outcome 이넘 클래스에 넣는다면?
        if (dealer.isBust()) {
            return Outcome.WIN.calculateProfit(bettingAmount);
        }
        if (isBust()) {
            return Outcome.LOSE.calculateProfit(bettingAmount);
        }
        if (isBlackJack() && dealer.isNotBlackJack()) {
            return Outcome.BLACKJACK.calculateProfit(bettingAmount);
        }
        if (isBlackJack() && dealer.isBlackJack()) {
            return Outcome.WIN.calculateProfit(bettingAmount);
        }
        return findPlayerOutcome(dealer.findPlayerDifference())
                .calculateProfit(bettingAmount);
    }

    public void settingBettingAmount(Integer bettingAmount) {
        this.bettingAmount = bettingAmount;
    }
}
