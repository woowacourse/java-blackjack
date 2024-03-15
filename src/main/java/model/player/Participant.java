package model.player;

import java.util.List;
import java.util.Objects;
import model.BettingMoney;
import model.Outcome;
import model.card.Card;

public class Participant extends User {

    private BettingMoney bettingMoney;

    public Participant(Name name, List<Card> cards, BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    public Double calculateProfit(Dealer dealer) { //todo 조건문을 Outcome 이넘 클래스에 넣는다면?
        if (dealer.isBust()) {
            return Outcome.WIN.calculateProfit(bettingMoney);
        }
        if (isBust()) {
            return Outcome.LOSE.calculateProfit(bettingMoney);
        }
        if (isBlackJack() && dealer.isNotBlackJack()) {
            return Outcome.BLACKJACK.calculateProfit(bettingMoney);
        }
        if (isBlackJack() && dealer.isBlackJack()) {
            return Outcome.WIN.calculateProfit(bettingMoney);
        }
        return findPlayerOutcome(dealer.findPlayerDifference())
                .calculateProfit(bettingMoney);
    }
}
