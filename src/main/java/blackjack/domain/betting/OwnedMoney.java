package blackjack.domain.betting;

import blackjack.domain.Ownable;
import blackjack.domain.participant.Name;
import java.util.Objects;

public class OwnedMoney implements Ownable<Name> {

    private final Name ownerName;
    private final Money money;

    public OwnedMoney(Name ownerName, Money money) {
        this.ownerName = ownerName;
        this.money = money;
    }

    public OwnedMoney(Name ownerName, int betAmount) {
        this(ownerName, new Money(betAmount));
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public Name getOwner() {
        return this.ownerName;
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
        return Objects.equals(ownerName, other.ownerName)
                && Objects.equals(money, other.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName, money);
    }
}
