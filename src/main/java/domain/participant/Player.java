package domain.participant;

import domain.money.BettingMoney;
import domain.card.Cards;

public class Player extends Participant {

    private final Name name;

    public Player(final Name name, final Cards cards, final BettingMoney bettingMoney) {
        super(bettingMoney, cards);
        this.name = name;
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public String getName() {
        return name.getName();
    }

    public void multiplyInterestOfPlayer(final Double profit) {
        this.money = money.calculateMoneyByProfit(profit);
    }
}
