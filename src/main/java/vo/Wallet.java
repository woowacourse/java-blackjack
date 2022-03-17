package vo;

public class Wallet {
    private final Name name;
    private final Money money;

    private Wallet(Name name, Money money) {
        this.name = name;
        this.money = money;
    }

    public static Wallet of(String name, int money) {
        return new Wallet(Name.from(name), Money.from(money));
    }

    public String getName() {
        return name.getName();
    }

    public int getMoney() {
        return money.getMoney();
    }
}
