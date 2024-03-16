package blackjack.domain.betting;

import blackjack.domain.Ownable;
import blackjack.domain.participant.Player;
import java.util.Objects;

public class OwnedMoney implements Ownable<Player> {

    private final Player owner;
    private final Money money;

    public OwnedMoney(Player owner, Money money) {
        this.owner = owner;
        this.money = money;
    }

    public OwnedMoney(Player owner, int betAmount) {
        this(owner, new Money(betAmount));
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OwnedMoney other = (OwnedMoney) o;
        return Objects.equals(owner, other.owner)
                && Objects.equals(money, other.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, money);
    }
}
