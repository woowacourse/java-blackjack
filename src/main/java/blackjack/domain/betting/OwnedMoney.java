package blackjack.domain.betting;

import blackjack.domain.participant.Name;

public class OwnedMoney {
    private final Name ownerName;
    private final Money money;

    public OwnedMoney(Name ownerName, Money money) {
        this.ownerName = ownerName;
        this.money = money;
    }

    public OwnedMoney(Name ownerName, int betAmount) {
        this(ownerName, new Money(betAmount));
    }

    public boolean ownedBy(Name ownerName) {
        return this.ownerName.equals(ownerName);
    }

    public Money getMoney() {
        return money;
    }

    public Name getOwnerName() {
        return ownerName;
    }
}
