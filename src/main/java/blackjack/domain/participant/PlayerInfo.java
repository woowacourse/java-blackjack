package blackjack.domain.participant;

public class PlayerInfo {

    private final Name name;
    private final BettingMoney bettingMoney;

    public PlayerInfo(final String name, final int bettingMoney) {
        this.name = new Name(name);
        this.bettingMoney = new BettingMoney(bettingMoney);
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
