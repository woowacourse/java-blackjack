package domain.player;

import domain.BettingMoney;
import domain.card.Card;

import java.util.List;

public class Player extends User {
    private BettingMoney bettingMoney;

    public Player(String name, List<Card> cards,int money) {
        super(name,cards);
        this.bettingMoney = new BettingMoney(money);
    }

    public int getBettingMoney() {
        return bettingMoney.getBettingMoney();
    }
}
