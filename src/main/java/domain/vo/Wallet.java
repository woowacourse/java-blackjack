package domain.vo;

public class Wallet {
    private static final int DEFAULT_ZERO_MONEY = 0;

    private final Name name;
    private final Money money;

    private Wallet(Name name, Money money) {
        this.name = name;
        this.money = money;
    }

    public static Wallet of(String name, int money) {
        return new Wallet(Name.from(name), Money.from(money));
    }

    public static Wallet of(String name) {
        return of(name, DEFAULT_ZERO_MONEY);
    }

    public String getName() {
        return name.getName();
    }

    public int getMoney() {
        return money.getMoney();
    }
}
