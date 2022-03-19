package blackjack.domain.entry;

public class Player {

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public boolean equalsName(Name name) {
        return this.name.equals(name);
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
