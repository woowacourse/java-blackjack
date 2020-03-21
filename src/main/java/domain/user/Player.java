package domain.user;

import domain.game.Money;
import domain.game.Rule;

public class Player extends User {
    private Money money;

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean isDrawable() {
        return !handCard.isOver();
    }

    public boolean isBlackJackByFirstCards() {
        return isBlackJack() && handCard.getSize() == Rule.getFirstDrawNumber();
    }
}
