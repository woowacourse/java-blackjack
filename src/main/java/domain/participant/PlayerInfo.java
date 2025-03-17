package domain.participant;

public class PlayerInfo {
    private final Name name;
    private final Money money;

    public PlayerInfo(Name name, Money money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name.getName();
    }

    public int getMoney() {
        return money.getMoney();
    }
}
