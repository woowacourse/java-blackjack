package blackjack.domain.user;

import blackjack.domain.Money;
import java.util.Objects;

public class Player extends User {

    private final Money money;

    public Player(String name, int money) {
        this(name, new Money(money));
    }

    public Player(String name, Money money) {
        super(name);
        this.money = money;
    }

    public boolean canHit() {
        return getScore().isNotBust() && !getScore().isBlackJack();
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
