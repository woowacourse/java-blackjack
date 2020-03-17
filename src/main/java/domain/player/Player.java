package domain.player;

import domain.Money;
import domain.card.Card;

import java.util.List;

public class Player extends User {
    private Money money;

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    public Player(String name, List<Card> cards,int money) {
        this(name,cards);
        this.money = new Money(money);
    }

    public Money getMoney() {
        return money;
    }
}
