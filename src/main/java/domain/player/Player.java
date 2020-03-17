package domain.player;

import domain.Money;
import domain.card.Card;

import java.util.List;

public class Player extends User {
    private Money money;

    public Player(String name, List<Card> cards) {
        super(name, cards);
        this.money = new Money(1);
    }

    public Player(String name, List<Card> cards,int money) {
        this(name,cards);
        this.money = new Money(money);
    }

    public int getMoney() {
        return money.getMoney();
    }
}
